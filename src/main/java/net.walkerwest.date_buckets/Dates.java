package net.walkerwest.date_buckets;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

class Dates {
	final String MORNING="06:00";
	final String EVENING="18:00";
	public static void main(String[] args) {
		System.out.println("Hello, world!");
		new Dates().run();
	}

	public void run() {
		ZoneId zoneId = ZoneId.of("UTC");
		ZonedDateTime beg = ZonedDateTime.
			of(2022, 11, 14, 06, 0, 0, 0000, zoneId);
		ZonedDateTime end = ZonedDateTime.
			of(2022, 11, 14, 17, 59, 59, 0000, zoneId);
		Integer[] buckets = null;
		try {
			buckets = getTimeBuckets(beg,end);
		} catch (Exception ex) { System.out.println(ex.getMessage()); }
		if (buckets!=null) {
			System.out.println("Buckets were found!");
		}
		//Duration myDur = Duration.between(beg,end);
		//System.out.println("The difference is "+myDur.toNanos());
	}

	public Integer[] getTimeBuckets(
			ZonedDateTime beg, 
			ZonedDateTime end) throws GreaterThan24HrsException {
		Integer[] returnArray = new Integer[3];
		if (Duration.between(beg,end).toNanos()>86400000000000L) {
			// 43199000000000
			// 86400000000000
			// System.out.println(Duration.between(beg,end).toNanos());
			throw new GreaterThan24HrsException();
		}
		return returnArray;
	}

	public class GreaterThan24HrsException extends Exception { 
		public GreaterThan24HrsException() {
			super("The difference between the beginning and ending " 
			  +"times should be less than or equal to 24 hours");
		}
	}
}
