package net.walkerwest.date_buckets;

public class Exceptions {
    public static class Over24HrsException extends Exception {
        public Over24HrsException() {
            super("The difference between the beginning and ending "
                    +"times should be less than or equal to 24 hours");
        }
    }

    public static class StartException extends Exception {
        public StartException() {
            super("The beginning timestamp could not be parse.");
        }
    }

    public static class StopException extends Exception {
        public StopException() {
            super("The ending timestamp could not be parse.");
        }
    }

    public static class BadZoneException extends Exception {
        public BadZoneException() {
            super("The timezone must be Etc/GMT-4 through -8.");
        }
    }

}
