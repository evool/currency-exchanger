package service;

import entity.RateEntity;
import parser.Parsing;
import saver.Saving;

public interface Sending {
	void send(RateEntity data);
	void setSaver(Saving saver);
	void setParser(Parsing parser);
}
