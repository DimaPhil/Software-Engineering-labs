import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.pf.DeciderBuilder;
import scala.Option;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private final static Map<String, String> map = new HashMap<>();
    static {
        map.put("child0", "Google");
        map.put("child1", "Yandex");
    }

    private static class RestartException extends RuntimeException {
        RestartException() {
            super();
        }
    }

    private static class StopException extends RuntimeException {
        StopException() {
            super();
        }
    }

    private static class EscalateException extends RuntimeException {
        EscalateException() {
            super();
        }
    }

    private static class ChildActor extends UntypedActor {

        @Override
        public void postStop() {
            System.out.println(self().path() + " was stopped");
        }

        @Override
        public void postRestart(Throwable cause) {
            System.out.println(self().path() + " was restarted after: " + cause);
        }

        @Override
        public void preRestart(Throwable cause, Option<Object> message) {
            System.out.println(self().path() + " is dying because of " + cause);
        }

        @Override
        public void onReceive(Object message) throws Throwable {
            if (message instanceof String) {
                if (message.equals("restart")) {
                    throw new RestartException();
                } else if (message.equals("stop")) {
                    throw new StopException();
                } else if (message.equals("escalate")) {
                    throw new EscalateException();
                }
            }
            if (message instanceof APIQuery) {
                APIQuery gq = (APIQuery) message;
                List<Result> result = gq.search();
                getSender().tell(result, self());
            }
        }
    }

    private static class MasterActor extends UntypedActor {
        private int number = 0;
        private PrintWriter writer;

        public MasterActor(PrintWriter writer) {
            this.writer = writer;
        }

        @Override
        public SupervisorStrategy supervisorStrategy() {
            return new OneForOneStrategy(false, DeciderBuilder
                    .match(RestartException.class, e -> OneForOneStrategy.restart())
                    .match(StopException.class, e -> OneForOneStrategy.stop())
                    .match(EscalateException.class, e -> OneForOneStrategy.escalate())
                    .build());
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onReceive(Object message) throws Throwable {
            if (message.equals("start")) {
                String name = "child" + number++;
                System.out.println("Create child: " + name);
                getContext().actorOf(Props.create(ChildActor.class), name);
            }
            if (message instanceof List) {
                System.out.println("Got answer");
                List<Result> m = (List<Result>) message;
                System.out.println(map.get(getSender().path().name()));
                for (Result aM : m) {
                    System.out.println(aM.getUrl() + ": " + aM.getTitle());
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        ActorSystem system = ActorSystem.create("MySystem");
        while (true) {
            try {
                String query = reader.readLine();
                ActorRef master = system.actorOf(
                        Props.create(MasterActor.class, writer), "master");
                master.tell("start", ActorRef.noSender());
                master.tell("start", ActorRef.noSender());

                system.actorSelection("user/master/child0").tell(new GoogleQuery(query), master);
                system.actorSelection("user/master/child1").tell(new YandexQuery(query), master);

            } catch (IOException e) {
                writer.close();
                e.printStackTrace();
            }
        }
    }
}
