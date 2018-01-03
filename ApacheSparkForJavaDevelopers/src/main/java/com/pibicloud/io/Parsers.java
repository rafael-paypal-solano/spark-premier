package com.pibicloud.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.pibicloud.io.model.Bill;
import com.pibicloud.io.model.BillItem;
import com.pibicloud.io.model.Product;
import com.pibicloud.model.DTTDetail;
import com.pibicloud.model.DTTHeader;

import scala.Tuple2;


public class Parsers {
	
	private static Logger logger = Logger.getLogger(Parsers.class);	
	public static final SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	public static final Pattern BILLITEM_PATTERN = Pattern.compile("([MJLA])" 			
			+ "(.{5})" //Item Id
			+ "(.{22})" //Item description
			+ "(.{4})" //Item quantity
			+ "(.(.{2}).{8})" //Item price
			+"(.{10})" // reference
			+".{3}.{27,28}");
	public final static Pattern CASHIER_PATTERN = Pattern.compile("^G(.{29})(.{3}).*");
	public final static Pattern TABLE_PATTERN = Pattern.compile("-(.{1})(.{5})(.{4})(.{3})(.{3})(.{6}).*");
	public static double parseDouble(String text, double defaultValue) {
		if(text == null)
			return defaultValue;
		 
		try {
			return Double.parseDouble(text.trim()); 
		}catch(NumberFormatException  e) {			
			logger.warn(e.getMessage(), e); 
		}
		
		return defaultValue;
	}
	
	public static double parseDouble(String text) {
		return parseDouble(text, 0.0);
	}
	
	public static Date parseTimestamp(String text) {
		if(text == null)
			return null;
		
		try {
			return TIMESTAMP_FORMATTER.parse(text.trim());
		}catch(ParseException  e) {	
			logger.warn(e.getMessage(), e);
		}
		
		return null;
		
	}
	
	private static double getBillItemPrice(Matcher matcher, String brokenValue) {
		
		if (brokenValue.contains("G")){
			return parseDouble(matcher.group(5).replaceAll(brokenValue, "").trim());
		}else {
			return parseDouble(matcher.group(5).trim());
		}
		
	}
	
	public static BillItem parseBillItem(String text) {
		if(text == null)
			return null;
		
		Matcher matcher = BILLITEM_PATTERN.matcher(text); 
		if(matcher.matches()) {
			BillItem billItem = new BillItem();		
			Product product = new Product();
			String brokenValue = matcher.group(6).trim();			
			String description = matcher.group(3).trim();
			double price = getBillItemPrice(matcher, brokenValue);
			double quantity = parseDouble(matcher.group(4).trim());
			String reference = matcher.group(7).trim(); //TODO: Use this value to look up Item in inventory records
			
			product.setReference(reference);
			product.setDescription(description);
			product.setPrice(price);
			
			billItem.setQuantity(quantity);
			billItem.setProduct(product);
			return billItem;
		}
		return null;
		
	}
	
	public static Matcher getStaticMatcher(DTTDetail detail) {
		String content = detail.getContent();
		
		return CASHIER_PATTERN.matcher(content);
	}
	
	public static Bill parseBill(DTTHeader header) {
		Matcher matcher = header.getMatcher();
		Bill bill = new Bill();		
		String billId = matcher.group(2); 
		double total = Parsers.parseDouble(matcher.group(4).trim());
		Date timestamp = Parsers.parseTimestamp(String.format("%s%s", matcher.group(1),matcher.group(5)));
		
		bill.setBillId(billId);
		bill.setTotal(total);
		bill.setTimestamp(timestamp);
		
		return bill;
	}
}
