package renato.gutierrez.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelMarca {
	@XmlElement(name = "item")
	private List<ItemMarca> items;

	public List<ItemMarca> getItems() {
		return items;
	}

	public void setItems(List<ItemMarca> items) {
		this.items = items;
	}

}
