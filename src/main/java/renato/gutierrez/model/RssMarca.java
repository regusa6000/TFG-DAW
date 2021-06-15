package renato.gutierrez.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class RssMarca {
	@XmlElement(name = "channel")
	private ChannelMarca channelMarca;

	public void setChannelMarca(ChannelMarca channelMarca) {
		this.channelMarca = channelMarca;
	}

	public ChannelMarca getChannelMarca() {
		return channelMarca;
	}



}
