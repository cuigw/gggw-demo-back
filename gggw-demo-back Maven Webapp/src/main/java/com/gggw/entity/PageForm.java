package com.gggw.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * ClassName:PageForm <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-18 上午11:24:11 <br/>
 * @author   cgw 
 */
public class PageForm {
	@NotNull
	private int draw;
	@NotNull
	private int start;
	@NotNull
	private int length;
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	
}

