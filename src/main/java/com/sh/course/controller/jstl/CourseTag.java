package com.sh.course.controller.jstl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CourseTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getRootLogger();
	
	private String myValue;
    
    public void setMyValue(String value) {
        this.myValue = value;
    }	
    
	@Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print( "hello! " + this.myValue );
        } catch(IOException e) {
        	log.error(e);
            throw new JspException("Error: doStartTag()" + e.getMessage());
        }       
        return SKIP_BODY;
    }
}
