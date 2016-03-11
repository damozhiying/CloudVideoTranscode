package cn.cntv.transcoder;

import java.io.File;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * parse the transcode parameters from a XML file.
 * 
 */
public class ParaParser {

	public static String parser(File inputXml) {
		String settings = "";
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element parameters = document.getRootElement();
			for (Iterator i = parameters.elementIterator(); i.hasNext();) {
				Element parameter = (Element) i.next();
				String name = parameter.getName();
				String value = parameter.getText();
				if (value.length() > 0) {
					if (name.intern() == "video_bps".intern()) {
						settings += "-b:v " + value + " ";
					}

					if (name.intern() == "video_size".intern()) {
						settings += "-s " + value + " ";
					}

					if (name.intern() == "frame_rate".intern()) {
						settings += "-r " + value + " ";
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return settings;
	}
}