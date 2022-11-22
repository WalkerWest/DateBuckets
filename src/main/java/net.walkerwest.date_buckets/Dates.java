package net.walkerwest.date_buckets;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import net.walkerwest.date_buckets.Exceptions.*;

class Dates {
	final String MORNING="06:00";
	final String EVENING="18:00";

	final static DateTimeFormatter formatter
			= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnnnnn VV");

	/*  Possible values for V are:
								 March		November
								 Daylight,  Standard,
		America/Los_Angeles ---> Etc/GMT+7, Etc/GMT+8
		America/Denver      ---> Etc/GMT+6, Etc/GMT+7
		America/Chicago     ---> Etc/GMT+5, Etc/GMT+6
		America/New_York    ---> Etc/GMT+4, Etc/GMT+5  */

	private List<ZoneId> validZones = Arrays.asList(new ZoneId[] {
		ZoneId.of("Etc/GMT+4"), ZoneId.of("Etc/GMT+5"),	ZoneId.of("Etc/GMT+6"),
		ZoneId.of("Etc/GMT+7"),	ZoneId.of("Etc/GMT+8")
	});

	public Integer[] getTimeBuckets(ZonedDateTime beg, ZonedDateTime end)
			throws Over24HrsException, BadZoneException {
		if(!validZones.contains(beg.getZone()) ||
				!validZones.contains(end.getZone()))
			throw new BadZoneException();
		Integer[] returnArray = new Integer[3];
		Long begLong = beg.toEpochSecond();
		Long endLong = end.toEpochSecond();
		if (Duration.between(beg,end).toNanos()>86400000000000L) {
			throw new Over24HrsException();
		}
		return returnArray;
	}

	public Integer[] getTimeBuckets(String begStr,String endStr) throws
			Over24HrsException, StartException, StopException,
			BadZoneException {
		ZonedDateTime myBegZdt = null;
		try { myBegZdt = ZonedDateTime.parse(begStr,formatter); }
		catch (Exception DateTimeParseException) { throw new StartException(); }
		ZonedDateTime myEndZdt = null;
		try { myEndZdt = ZonedDateTime.parse(endStr,formatter); }
		catch (Exception DateTimeParseException) { throw new StopException(); }
		return getTimeBuckets(myBegZdt, myEndZdt);
	}

}
