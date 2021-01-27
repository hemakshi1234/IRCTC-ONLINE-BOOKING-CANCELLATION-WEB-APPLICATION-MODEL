package com.irctc.baseclasses;

public class BookingMaster{
	private int booking_master_id;
	private int booking_master_user_id;
	private java.util.Date booking_master_date_of_journey;
	private double booking_master_no_of_pass;
	private String booking_master_pnr;
	private String booking_master_class;
	private String booking_master_remarks;
	private String booking_master_flag;
	private int booking_master_train_id;

	
	public int getBooking_master_id(){
		return booking_master_id;
	}

	public void setBooking_master_id(int booking_master_id){
		this.booking_master_id=booking_master_id;
	}

	
	public int getBooking_master_user_id(){
		return booking_master_user_id;
	}

	public void setBooking_master_user_id(int booking_master_user_id){
		this.booking_master_user_id=booking_master_user_id;
	}

	public java.util.Date getBooking_master_date_of_journey(){
		return booking_master_date_of_journey;
	}

	public void setBooking_master_date_of_journey(java.util.Date booking_master_date_of_journey){
		this.booking_master_date_of_journey=booking_master_date_of_journey;
	}

	public double getBooking_master_no_of_pass(){
		return booking_master_no_of_pass;
	}

	public void setBooking_master_no_of_pass(double booking_master_no_of_pass){
		this.booking_master_no_of_pass=booking_master_no_of_pass;
	}

	public String getBooking_master_pnr(){
		return booking_master_pnr;
	}

	public void setBooking_master_pnr(String booking_master_pnr){
		this.booking_master_pnr=booking_master_pnr;
	}

	public String getBooking_master_class(){
		return booking_master_class;
	}

	public void setBooking_master_class(String booking_master_class){
		this.booking_master_class=booking_master_class;
	}

	public String getBooking_master_remarks(){
		return booking_master_remarks;
	}

	public void setBooking_master_remarks(String booking_master_remarks){
		this.booking_master_remarks=booking_master_remarks;
	}

	public String getBooking_master_flag(){
		return booking_master_flag;
	}

	public void setBooking_master_flag(String booking_master_flag){
		this.booking_master_flag=booking_master_flag;
	}

	public int getBooking_master_train_id(){
		return booking_master_train_id;
	}

	public void setBooking_master_train_id(int booking_master_train_id){
		this.booking_master_train_id=booking_master_train_id;
	}
}