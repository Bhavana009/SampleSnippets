package com.example.samplesnippets;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Stack;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarView extends Activity {

	public GregorianCalendar month, itemmonth;// calendar instances.

	public CalendarAdapter adapter, adapter1;// adapter instance
	public Handler handler;// for grabbing some event values for showing the dot
							// marker.
	public ArrayList<String> items; // container to store calendar items which
	Date chckdt1,chckdat2 ;						// needs showing the event marker
//	ArrayList<String> event;
//	LinearLayout rLayout;
	ArrayList<String> date;
	ArrayList<String> desc;
	private static GridView gridview;
	String selecteddate, gridvalueString;
	Button buttonDialog, buttonDialog2;
	final Context context = this;
	TextView title;
static int btn_click;
	static int cin_day, cin_mon, cin_year;
	static int cout_day = 0, cout_mon = 0, cout_year = 0;
	static int inc_day = 0, inc_mon = 0, inc_year = 0;
	String selected_day, selected_mon, selected_year;
	boolean flag = false;
	String sel_date = "";
	DateFormat df;
	String today_date;
	static String title_year;
	static TextView txt_SUN,txt_Mon,txt_TUE,txt_WED,txt_THU,txt_FRI,txt_SAT;
	TextView dateTxt_in, monthTxt_in, dayTxt_in, dateTxt_out, monthTxt_out,
			dayTxt_out;
	LinearLayout checkoutLayout, checkinLayout;
	DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd", Locale.US);
	String[] txt_month={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};

	// TextView txt_selected;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cal_button);

		checkinLayout = (LinearLayout) findViewById(R.id.checkinLayout);
		checkoutLayout = (LinearLayout) findViewById(R.id.checkoutLayout);
		
		dateTxt_in = (TextView) findViewById(R.id.dateTxt_in);
		monthTxt_in = (TextView) findViewById(R.id.monthTxt_in);
		dayTxt_in = (TextView) findViewById(R.id.dayTxt_in);

		dateTxt_out = (TextView) findViewById(R.id.dateTxt_out);
		monthTxt_out = (TextView) findViewById(R.id.monthTxt_out);
		dayTxt_out = (TextView) findViewById(R.id.dayTxt_out);

		Locale.setDefault(Locale.US);
		month = (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth = (GregorianCalendar) month.clone();

		items = new ArrayList<String>();

		// get current date
		Date curdate = month.getTime();
		System.out.println("currend date(date format)" + curdate);
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		sel_date = df.format(curdate);
		System.out.println("currend date(string format)" + sel_date);
		adapter = new CalendarAdapter(this, month, sel_date, sel_date,sel_date);

		String cur_date = adapter.getCurrentDate();
		today_date = cur_date;
		parseDate(cur_date);

		buttonDialog = (Button) findViewById(R.id.btn);
		buttonDialog2 = (Button) findViewById(R.id.btn2);
		
		buttonDialog.setText("" + cin_year + "-" + cin_mon + "-" + cin_day);
		String cm_dayy="0"+cin_mon;
		int cm_wday=Integer.parseInt(cm_dayy);
		dateTxt_in.setText(""+cin_day);
		monthTxt_in.setText(""+txt_month[cin_mon-1]+"'"+subString(cin_year));
		
		
//		String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE",curdate);
//		dayTxt_in.setText(dayOfTheWeek);
		SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = inFormat.parse(""+cin_day+"-"+cin_mon+"-"+cin_year);
			SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
			String goal = outFormat.format(date); 
			dayTxt_in.setText(goal);
			System.out.println("goal"+goal);
			Toast.makeText(this,""+goal, 1).show();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean z = incrementday();
		cout_year = inc_year;
		cout_mon = inc_mon;
		cout_day = inc_day;

		buttonDialog2.setText("" + cout_year + "-" + cout_mon + "-" + cout_day);
		dateTxt_out.setText(""+cout_day);
		monthTxt_out.setText(""+txt_month[cout_mon-1]+"'"+subString(cout_year));
//		String dayOfTheWeek2 = (String) android.text.format.DateFormat.format("EEE", new Date(cout_year,cout_mon,cout_day));
//		dayTxt_out.setText(dayOfTheWeek2);
		SimpleDateFormat inFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		Date date1;
		try {
			date1 = inFormat1.parse(""+cout_day+"-"+cout_mon+"-"+cout_year);
			SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
			String goal = outFormat.format(date1); 
			dayTxt_out.setText(goal);
			System.out.println("goal"+goal);
			Toast.makeText(this,""+goal, 1).show();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("increment val" + inc_day + "--" + inc_mon + "---"
				+ inc_year);
		System.out.println("out values" + cout_day + "--" + cout_mon + "---"
				+ cout_year);

		calDiff();

		checkinLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btn_click=1;

				flag = false;
				String btn_text = buttonDialog.getText().toString();
//				dateTxt_in.getText().toString();
				parseDate(btn_text);

				// Toast.makeText(context, "Date is:"+cur_date, 1).show();
				setUp(cin_year, cin_mon, cin_day);

			}
		});

		buttonDialog2 = (Button) findViewById(R.id.btn2);
		checkoutLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btn_click=2;
				flag = true;

				String btn2_text = buttonDialog2.getText().toString();
				parsecoutDate(btn2_text);
				setUp(cout_year, cout_mon, cout_day);
//				String ch_in=""+cin_year+"-0"+cin_mon+"-"+cin_day,ch_out=""+cout_year+"-0"+cout_mon+"-"+cout_day;
//				adapter = new CalendarAdapter(v.getContext(), month, ch_out, ch_in);
//				gridview.setAdapter(adapter);
				// setUp(inc_year, inc_mon, inc_day);
				// setUp(cout_year,cout_mon,cout_day);
			}
		});
	}

	public void setUp(int year, int mon, int day) {
		AlertDialog.Builder builder = new AlertDialog.Builder(CalendarView.this);
		builder.setCancelable(true);
		builder.setNegativeButton("Ignore",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// finish the application
						CalendarView.this.finish();
					}
				});

		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.calendar);
		
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		dialog.getWindow().setGravity(Gravity.CENTER);
//		rLayout = (LinearLayout) dialog.findViewById(R.id.text);
		gridview = (GridView) dialog.findViewById(R.id.gridview);
		txt_SUN=(TextView)dialog.findViewById(R.id.txt_SUN);
		txt_Mon=(TextView)dialog.findViewById(R.id.txt_Mon);
		txt_TUE=(TextView)dialog.findViewById(R.id.txt_TUE);
		txt_WED=(TextView)dialog.findViewById(R.id.txt_WED);
		txt_THU=(TextView)dialog.findViewById(R.id.txt_THU);
		txt_FRI=(TextView)dialog.findViewById(R.id.txt_FRI);
		txt_SAT=(TextView)dialog.findViewById(R.id.txt_SAT);

		String monStr = "" + mon;
		if (monStr.length() == 1) {
			monStr = "0" + mon;
		}

		String dayStr = "" + day;
		if (dayStr.length() == 1) {
			dayStr = "0" + day;
		}
		String sel_date2 = "" + year + "-" + monStr + "-" + dayStr;
		// month = (GregorianCalendar) GregorianCalendar.getInstance();
		// change month Georgee............

		String[] curdate_array = sel_date.split("-");
		int cur_year = Integer.parseInt(curdate_array[0]
				.replaceFirst("^0*", ""));
		int cur_mon = Integer
				.parseInt(curdate_array[1].replaceFirst("^0*", ""));

		cur_mon = cur_mon - 1;

		mon = mon - 1;
		while (month.get(GregorianCalendar.MONTH) != mon) {
			if (month.get(GregorianCalendar.MONTH) < mon) {
				month.set(GregorianCalendar.MONTH,
						month.get(GregorianCalendar.MONTH) + 1);
			} else {
				month.set(GregorianCalendar.MONTH,
						month.get(GregorianCalendar.MONTH) - 1);
			}
		}

		int mon_val = month.get(GregorianCalendar.MONTH);
		int year_val = month.get(GregorianCalendar.YEAR);

		adapter = new CalendarAdapter(this, month, sel_date2, sel_date,sel_date);
		gridview.setAdapter(adapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		title = (TextView) dialog.findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		selecteddate = title.getText().toString();
		System.out.println("datedate" + selecteddate);
		String[] title_array = selecteddate.split(" ");
		title_year = title_array[1].replaceFirst("^0*", "");
		RelativeLayout previous = (RelativeLayout) dialog
				.findViewById(R.id.previous);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String[] curdate_array = sel_date.split("-");
				int cur_year = Integer.parseInt(curdate_array[0].replaceFirst(
						"^0*", ""));
				int cur_mon = Integer.parseInt(curdate_array[1].replaceFirst(
						"^0*", ""));

				int mon_val = month.get(GregorianCalendar.MONTH);
				mon_val = mon_val + 1;
				int year_val = month.get(GregorianCalendar.YEAR);

				if ((mon_val > cur_mon) || (year_val > cur_year)) {
					setPreviousMonth();
					refreshCalendar();
				}
			}
		});

		RelativeLayout next = (RelativeLayout) dialog.findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();

			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// removing the previous view if added

//				if (((LinearLayout) rLayout).getChildCount() > 0) {
//					((LinearLayout) rLayout).removeAllViews();
//				}
				desc = new ArrayList<String>();
				date = new ArrayList<String>();
				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalendarAdapter.dayString
						.get(position);

				String[] separatedTime = selectedGridDate.split("-");
				gridvalueString = separatedTime[2].replaceFirst("^0*", "");
				selected_day = separatedTime[2].replaceFirst("^0*", "");
				selected_mon = separatedTime[1].replaceFirst("^0*", "");
				selected_year = separatedTime[0].replaceFirst("^0*", "");
				if (!flag) {
					cin_year = StringToInt(selected_year);
					cin_mon = StringToInt(selected_mon);
					cin_day = StringToInt(selected_day);
					buttonDialog.setText("" + cin_year + "-" + cin_mon + "-"+ cin_day);
					dateTxt_in.setText(""+cin_day);
					monthTxt_in.setText(""+txt_month[cin_mon-1]+"'"+subString(cin_year));
//					String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEE", new Date(cin_year,cin_mon,cin_day));
//					dayTxt_in.setText(dayOfTheWeek);
					SimpleDateFormat inFormat4 = new SimpleDateFormat("dd-MM-yyyy");
					Date date4;
					try {
						date4 = inFormat4.parse(""+cin_day+"-"+cin_mon+"-"+cin_year);
						SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
						String goal = outFormat.format(date4); 
						dayTxt_in.setText(goal);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// if((cout_year==cin_year)||(cout_mon==cin_mon))
					// if(cout_day<=cin_day)
					// {
					// incrementday();
					// cout_year=inc_year;
					// cout_mon=inc_mon;
					// cout_day=inc_day;
					// buttonDialog2.setText(""+cout_year+"-"+cout_mon+"-"+cout_day);
					// }
					//
					// incrementday();
					// cout_year=inc_year;
					// cout_mon=inc_mon;
					// cout_day=inc_day;
					// buttonDialog2.setText(""+cout_year+"-"+cout_mon+"-"+cout_day);

					Date dout = new Date(cout_year, cout_mon, cout_day);
					Date din = new Date(cin_year, cin_mon, cin_day);

					if (dout.compareTo(din) > 0) {

					} else {
						incrementday();
						cout_year = inc_year;
						cout_mon = inc_mon;
						cout_day = inc_day;
						buttonDialog2.setText("" + cout_year + "-" + cout_mon
								+ "-" + cout_day);
						
						dateTxt_out.setText(""+cout_day);
						monthTxt_out.setText(""+txt_month[cout_mon-1]+"'"+subString(cout_year));
//						String dayOfTheWeek1 = (String) android.text.format.DateFormat.format("EEE", new Date(cout_year,cout_mon,cout_day));
//						dayTxt_out.setText(dayOfTheWeek1);
						SimpleDateFormat inFormat2 = new SimpleDateFormat("dd-MM-yyyy");
						Date date2;
						try {
							date2 = inFormat2.parse(""+cout_day+"-"+cout_mon+"-"+cout_year);
							SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
							String goal = outFormat.format(date2); 
							dayTxt_out.setText(goal);
//							System.out.println("goal"+goal);
//							Toast.makeText(this,""+goal, 1).show();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else {
					cout_year = StringToInt(selected_year);
					cout_mon = StringToInt(selected_mon);
					cout_day = StringToInt(selected_day);
				
					
					// Toast.makeText(context,
					// ""+cout_day+"--"+cout_mon+"--"+cout_year, 1).show();
					
					if ((cout_day > cin_day) || (cout_mon > cin_mon)
							|| (cout_year > cin_year)) {
						buttonDialog2.setText("" + cout_year + "-" + cout_mon
								+ "-" + cout_day);
						dateTxt_out.setText(""+cout_day);
						monthTxt_out.setText(""+txt_month[cout_mon-1]+"'"+subString(cout_year));
//						String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEE", new Date(cout_year,cout_mon,cout_day));
//						dayTxt_out.setText(dayOfTheWeek);
						SimpleDateFormat inFormat3 = new SimpleDateFormat("dd-MM-yyyy");
						Date date3;
						try {
							date3 = inFormat3.parse(""+cout_day+"-"+cout_mon+"-"+cout_year);
							SimpleDateFormat outFormat = new SimpleDateFormat(""
									+ ""
									+ "");
							String goal = outFormat.format(date3); 
							dayTxt_out.setText(goal);
//							System.out.println("goal"+goal);
//							Toast.makeText(this,""+goal, 1).show();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						
						Toast.makeText(context,
								"Date must be greater than Chekin Date", 1)
								.show();
						

					}

				}

				System.out.println("selected date::" + gridvalueString);
				// buttonDialog.setText(""+gridvalueString);
				// buttonDialog.setText(""+gridvalueString);

				// System.out.println("hello display date"+txt_selected.getText().toString());

				// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				// navigate to next or previous month on clicking
				// offdays.

				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter()).setSelected(v);

				for (int i = 0; i < Utility.startDates.size(); i++) {
					if (Utility.startDates.get(i).equals(selectedGridDate)) {
						desc.add(Utility.nameOfEvent.get(i));
					}
				}

				if (desc.size() > 0) {
					for (int i = 0; i < desc.size(); i++) {
						TextView rowTextView = new TextView(CalendarView.this);
						// set some properties of rowTextView or
						// something
						rowTextView.setText("Event:" + desc.get(i));
						// Toast.makeText(context, "Date is:"+desc.get(i),
						// 1).show();
						rowTextView.setTextColor(Color.BLACK);

						// add the textview to the linearlayout
//						rLayout.addView(rowTextView);
					}
				}
				desc = null;
				calDiff();
				// dialog.cancel();
				dialog.dismiss();
			}
		});
		// txt_selected.setText(gridvalueString+" "+selecteddate);
		dialog.show();
	}

	protected void setNextMonth() {

		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMaximum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}
	}

	protected void setPreviousMonth() {

		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

	}

	// protected void showToast(String string) {
	// Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	//
	// }

	public void refreshCalendar() {
		// TextView title = (TextView)findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		selecteddate = title.getText().toString();
		System.out.println("datedate" + selecteddate);

	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();

			// Print dates of the current week
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			String itemvalue;
//			event = Utility.readCalendarEvent(CalendarView.this);
//			Log.d("=====Event====", event.toString());
			Log.d("=====Date ARRAY====", Utility.startDates.toString());

			for (int i = 0; i < Utility.startDates.size(); i++) {
				itemvalue = df.format(itemmonth.getTime());
				System.out.println("itemvalue" + itemvalue);
				itemmonth.add(GregorianCalendar.DATE, 1);
				items.add(Utility.startDates.get(i).toString());
			}
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};

	public void parseDate(String date) {
		// 2014-01-14
		String[] str = date.split("-");
		int size = str.length;
		cin_year = Integer.parseInt(str[0]);
		cin_mon = Integer.parseInt(str[1]);
		cin_day = Integer.parseInt(str[2]);
		System.out.println("checkin date---" + cin_day + "" + cin_mon + ""
				+ cin_year);
	}

	public void parsecoutDate(String date) {
		// 2014-01-14
		String[] str = date.split("-");
		cout_year = Integer.parseInt(str[0]);
		cout_mon = Integer.parseInt(str[1]);
		cout_day = Integer.parseInt(str[2]);
		System.out.println("checkout date---" + cout_day + "" + cout_mon + ""
				+ cout_year);
	}

	public int StringToInt(String str) {
		int n = Integer.parseInt(str);
		return n;
	}

	public boolean incrementday() {

		// inc_day=cin_day+1;
		System.out.println("countval" + inc_day);

		if (inc_year % 4 == 0) {

			// leap year
			if ((cin_mon == 1) || (cin_mon == 3) || (cin_mon == 5)
					|| (cin_mon == 7) || (cin_mon == 8) || (cin_mon == 10)
					|| (cin_mon == 12)) {
				if (cin_day == 31) {
					if (cin_mon == 12) {
						inc_mon = 1;
						inc_year = cin_year + 1;
						inc_day = 1;
					} else {
						inc_day = 1;
						inc_mon = cin_mon + 1;
						inc_year = cin_year;
					}

				} else {
					inc_day = cin_day + 1;
					inc_mon = cin_mon;
					inc_year = cin_year;
				}
			}

			else if ((cin_mon == 4) || (cin_mon == 6) || (cin_mon == 9)
					|| (cin_mon == 11)) {
				if (cin_day == 30) {
					inc_day = 1;
					inc_mon = cin_mon + 1;
				} else {
					inc_day = cin_day + 1;
					inc_mon = cin_mon;
				}
			} else {
				if (cin_day == 29) {
					inc_day = 1;
					inc_mon = cin_mon + 1;
				} else {
					inc_day = cin_day + 1;
					inc_mon = cin_mon;
				}
			}
		} else {
			// non leap year
			if ((cin_mon == 1) || (cin_mon == 3) || (cin_mon == 5)
					|| (cin_mon == 7) || (cin_mon == 8) || (cin_mon == 10)
					|| (cin_mon == 12)) {
				if (cin_day == 31) {
					inc_day = 1;
					inc_mon = cin_mon + 1;
				} else {
					inc_day = cin_day + 1;
					inc_mon = cin_mon;
				}
			}

			else if ((cin_mon == 4) || (cin_mon == 6) || (cin_mon == 9)
					|| (cin_mon == 11)) {
				if (cin_day == 30) {
					inc_day = 1;
					inc_mon = cin_mon + 1;
				} else {
					inc_day = cin_day + 1;
					inc_mon = cin_mon;
				}
			} else {
				if (cin_day == 28) {
					inc_day = 1;
					inc_mon = cin_mon + 1;
				} else {
					inc_day = cin_day + 1;
					inc_mon = cin_mon;
				}
			}
		}
		return true;

	}

	public void calDiff() {
		Date d1 = new Date(cin_year, cin_mon, cin_day);
		Date d2 = new Date(cout_year, cout_mon, cout_day);
		long diff = d2.getTime() - d1.getTime();
		long l = 1000 * 60 * 60 * 24;
		long num_days = diff / l;
		System.out.println("num_days" + num_days);
		if (num_days > 0) {
			Toast.makeText(context, "num_days" + num_days, 1).show();
		}
	}
	public String subString(int n)
	{
		String str=""+n;
		String strr=str.substring(2);
		return strr;
	}
}
