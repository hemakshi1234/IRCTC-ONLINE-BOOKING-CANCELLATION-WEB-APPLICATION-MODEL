package com.irctc.baseclasses;


public class UserType
{
	private int user_id;
	private String type;
	private String remark;
	private String flag;
	
	//Setters 
	
	public void setUserId(int user_id){
		this.user_id=user_id;
	}
	
	public void setType(String ty)
	{
		this.type=ty;
	}

	public void setRemark(String rem)
	{
		this.remark=rem;
	}

	public void setFlag(String fg)
	{
		this.flag=fg;
	}
	
	
	//Getters
	
	public int getUserId()
	{
		return user_id;
	}
	
	public String getType()
	{
		return  type;
	}
	
	public String getRemark()
	{
		return  remark;
	}
	
	public String getFlag()
	{
		return  flag;
	}
		
	
}