package service;

import entity.RateEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import parser.Parsing;
import saver.Saving;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Sender implements Sending {
	
	private Saving saver;
	private Parsing parser;
	
	@Override
	public void send(RateEntity data) {
		if(parser == null) {
			saver.save(data);
		} else {
			saver.save(parser.parse(data));
		}
	}
}
