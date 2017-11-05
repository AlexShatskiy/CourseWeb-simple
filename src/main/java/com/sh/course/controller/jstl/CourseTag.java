package com.sh.course.controller.jstl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class CourseTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String myValue;
    
    public void setMyValue(String value) {
        this.myValue = value;
    }	
    
	@Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print( "hello! " + this.myValue );
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }       
        return SKIP_BODY;
    }
}