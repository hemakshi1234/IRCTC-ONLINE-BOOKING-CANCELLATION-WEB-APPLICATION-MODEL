package com.irctc.baseclasses;

public class SeatAvailability{
	private int seat_availability_id; 
	private int seat_availability_train_id;
	private String seat_availability_class;
	private String seat_availability_coachno;
	private double seat_availability_seatno;
	private String seat_availability_berthtype;
	private String seat_availability_remarks;
	private String seat_availability_flag;

	
	public int getSeat_availability_id(){
		return seat_availability_id;
	}

	public void setSeat_availability_id(int seat_availability_id){
		this.seat_availability_id=seat_availability_id;
	}

	
	public int getSeat_availability_train_id(){
		return seat_availability_train_id;
	}

	public void setSeat_availability_train_id(int seat_availability_train_id){
		this.seat_availability_train_id=seat_availability_train_id;
	}

	public String getSeat_availability_class(){
		return seat_availability_class;
	}

	public void setSeat_availability_class(String seat_availability_class){
		this.seat_availability_class=seat_availability_class;
	}

	public String getSeat_availability_coachno(){
		return seat_availability_coachno;
	}

	public void setSeat_availability_coachno(String seat_availability_coachno){
		this.seat_availability_coachno=seat_availability_coachno;
	}

	public double getSeat_availability_seatno(){
		return seat_availability_seatno;
	}

	public void setSeat_availability_seatno(double seat_availability_seatno){
		this.seat_availability_seatno=seat_availability_seatno;
	}

	public String getSeat_availability_berthtype(){
		return seat_availability_berthtype;
	}

	public void setSeat_availability_berthtype(String seat_availability_berthtype){
		this.seat_availability_berthtype=seat_availability_berthtype;
	}

	public String getSeat_availability_remarks(){
		return seat_availability_remarks;
	}

	public void setSeat_availability_remarks(String seat_availability_remarks){
		this.seat_availability_remarks=seat_availability_remarks;
	}

	public String getSeat_availability_flag(){
		return seat_availability_flag;
	}

	public void setSeat_availability_flag(String seat_availability_flag){
		this.seat_availability_flag=seat_availability_flag;
	}
}