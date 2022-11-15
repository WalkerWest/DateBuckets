package net.walkerwest.date_buckets;

import net.walkerwest.date_buckets.Dates;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import org.junit.*;

public class datesTest {

	@Test(expected=Dates.GreaterThan24HrsException.class)
	public void bigRangeTest() throws Dates.GreaterThan24HrsException {
		ZoneId zoneId = ZoneId.of("UTC");
		ZonedDateTime beg = ZonedDateTime.
			of(2022, 11, 14, 06, 0, 0, 0000, zoneId);
		ZonedDateTime end = ZonedDateTime.
			of(2022, 11, 15, 17, 59, 59, 0000, zoneId);
		Dates myDates = new Dates();
		try { Integer[] buckets = myDates.getTimeBuckets(beg,end); } 
		catch (Dates.GreaterThan24HrsException ex) { throw ex; }
	}

}
