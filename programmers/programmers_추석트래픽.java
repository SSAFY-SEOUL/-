import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

class Solution {
	private static class Log {
		Date start, end;
		public Log(Date start, Date end) {
			super();
			this.start = start;
			this.end = end;
		}
	}

	public static int solution(String[] lines) {
		int N = lines.length;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Log[] logs = new Log[N];

		int max = 0;
		
		try {
			Date start, end;
			double t;
			Calendar cal = Calendar.getInstance();
			
			for(int i = 0; i < N; ++i) {
				StringTokenizer st = new StringTokenizer(lines[i], " ");
				end = format.parse(st.nextToken() + " " + st.nextToken());
				t = Double.parseDouble(st.nextToken().split("s")[0]);
				cal.setTime(end);
				cal.add(Calendar.MILLISECOND, (int)(t * -1000) + 1);
				start = new Date(cal.getTimeInMillis());
				logs[i] = new Log(start, end);
			}
			
			int startCnt, endCnt;
			for (int i = 0; i < N; ++i) {
				start = logs[i].start;
				cal.setTime(start);
				cal.add(Calendar.MILLISECOND, 999);
				end = new Date(cal.getTimeInMillis());
				startCnt = 1;
			
				for (int j = 0; j < N; ++j) {
					if (i == j) continue;
					if (logs[j].start.before(start) && logs[j].end.before(start)) continue;
					if (logs[j].start.after(end) && logs[j].end.after(end)) continue;
					
					startCnt++;
					
				}
				if (startCnt > max) max = startCnt;
				
				
				start = logs[i].end;
				cal.setTime(start);
				cal.add(Calendar.MILLISECOND, 999);
				end = new Date(cal.getTimeInMillis());
				endCnt = 1;
				
				for (int j = 0; j < N; ++j) {
					if (i == j) continue;
					if (logs[j].start.before(start) && logs[j].end.before(start)) continue;
					if (logs[j].start.after(end) && logs[j].end.after(end)) continue;
					
					endCnt++;
				}
				if (endCnt > max) max = endCnt;
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return max;
	}
}