package service;

import entity.CurrencyEntity;
import service.parser.Parsing;
import service.saver.Saving;

public class Sender implements Sending {
	private Parsing parser;
	private Saving saver;
	
	public Sender(Saving saver) {
		this.saver = saver;
	}
	
	public Sender(Saving saver, Parsing parser) {
		this.saver = saver;
		this.parser = parser;
	}
	
	@Override
	public void send(CurrencyEntity data) {
		if(parser == null) {
			saver.save(data);
		} else {
			saver.save(parser.parse(data));
		}
	}
}
