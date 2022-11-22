package net.walkerwest.date_buckets;

import net.walkerwest.date_buckets.Exceptions.*;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import org.junit.*;

public class datesTest {

	@Test(expected= Over24HrsException.class)
	public void bigRangeTest() throws Over24HrsException, BadZoneException {
		ZoneId zoneId = ZoneId.of("Etc/GMT+5");
		ZonedDateTime beg = ZonedDateTime.
			of(2022, 11, 14, 06, 0, 0, 0000, zoneId);
		ZonedDateTime end = ZonedDateTime.
			of(2022, 11, 15, 06, 0, 0, 0001, zoneId);
		Dates myDates = new Dates();
		try { Integer[] buckets = myDates.getTimeBuckets(beg,end); } 
		catch (Over24HrsException ex) { throw ex; }
	}

	@Test(expected=StartException.class)
	public void passInBadStartStringTest()
			throws StartException, BadZoneException {
		Dates myDates = new Dates();
		try {
			myDates.getTimeBuckets(
					"2022-11-14 06:00:00.000","2022-11-14 06:00:00.000");
		}
		catch (StopException e) { e.printStackTrace(); }
		catch (Over24HrsException e) { e.printStackTrace(); }
	}

	@Test(expected=StopException.class)
	public void passInBadStopStringTest()
			throws StopException, BadZoneException {
		Dates myDates = new Dates();
		try {
			myDates.getTimeBuckets(
					"2022-11-14 06:00:00.000000000 America/Chicago",
					"2022-11-14 06:00:00.000");
		}
		catch (StartException e) { e.printStackTrace(); }
		catch (Over24HrsException e) { e.printStackTrace(); }
	}

	@Test(expected= Over24HrsException.class)
	public void passInStringsOver24Test()
			throws Over24HrsException, BadZoneException {
		Dates myDates = new Dates();
		try {
			myDates.getTimeBuckets(
					"2022-11-14 06:00:00.000000000 Etc/GMT+6",
					"2022-11-15 06:00:00.000000001 Etc/GMT+6");
		} catch (StartException e) {
			e.printStackTrace();
		} catch (StopException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void exactly24HrsTest() throws Over24HrsException, BadZoneException {
		//ZoneId zoneId = ZoneId.of("UTC");
		ZoneId zoneId = ZoneId.of("Etc/GMT+5");
		ZonedDateTime beg = ZonedDateTime.
			of(2022, 11, 14, 06, 0, 0, 0000, zoneId);
		ZonedDateTime end = ZonedDateTime.
			of(2022, 11, 15, 06, 0, 0, 0000, zoneId);
		Dates myDates = new Dates();
		try { Integer[] buckets = myDates.getTimeBuckets(beg,end); } 
		catch (Over24HrsException ex) { throw ex; }
	}

	@Test(expected= Over24HrsException.class)
	public void dstTest() throws Over24HrsException, BadZoneException {
		Dates myDates = new Dates();
		try {
			myDates.getTimeBuckets(
					"2022-11-07 02:00:00.000000000 Etc/GMT+5",
					"2022-11-08 01:01:00.000000000 Etc/GMT+6");
		}
		catch (StartException e) { e.printStackTrace(); }
		catch (StopException e) { e.printStackTrace(); }
	}

}
