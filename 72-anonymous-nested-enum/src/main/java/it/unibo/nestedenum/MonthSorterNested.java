package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */ 
public final class MonthSorterNested implements MonthSorter {
    enum Month {
        JANUARY("january", 1, 31),
        FEBRUARY("february", 2, 28),
        MARCH("march", 3, 31),
        APRIL("april", 4, 30),
        MAY("may", 5, 31),
        JUNE("june", 6, 30),
        JULY("july", 7, 31),
        AUGUST("august", 8, 31),
        SEPTEMBER("september", 9, 30),
        OCTOBER("october", 10, 31),
        NOMBER("november", 11, 30),
        DECEMBER("december", 12, 31);

        private final String name;
        private final int order;
        private final int days;

        private Month(final String name, final int order, final int days){
            this.name = name;
            this.order = order;
            this.days = days;
        }

        public String getName() {
            return this.name;
        }

        public int getOrder() {
            return this.order;
        }

        public int getDays() {
            return this.days;
        }

        public static Month fromString(final String name) {
            Month month = null;
            for (Month m: Month.values()) {
                if(m.getName().contains(name.toLowerCase())) {
                    if(month != null) {
                        throw new IllegalArgumentException("Ambiguous name " + name);
                    }
                    month = m;
                }
            }
            if(month == null) {
                throw new IllegalArgumentException("Invalid name " + name);
            }
            return month;
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        class SortByDate implements Comparator<String> {
            @Override
            public int compare(String arg0, String arg1) {
                return Month.fromString(arg0).getDays() - Month.fromString(arg1).getDays();
            }
        }
        return new SortByDate();   
    }

    @Override
    public Comparator<String> sortByOrder() {
        class SortByMonthOrder implements Comparator<String> {
            @Override
            public int compare(String arg0, String arg1) {
                return Month.fromString(arg0).getOrder() - Month.fromString(arg1).getOrder();
            }
        }
        return new SortByMonthOrder();
    }
    
}
