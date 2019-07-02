package tablas;

public class CRM {
	
	public String nombre, email;
	public int telefono, DecoWifi_pack, Wifi_Signal_Expansion, Smart_Plugs_Pack, Smart_Bulbs_Pack, Wifi_Surveillance_Cameras;
	
	public CRM(String nombre, String email, int telefono, int decoWifi_pack, int wifi_Signal_Expansion,
			int smart_Plugs_Pack, int smart_Bulbs_Pack, int wifi_Surveillance_Cameras) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		DecoWifi_pack = decoWifi_pack;
		Wifi_Signal_Expansion = wifi_Signal_Expansion;
		Smart_Plugs_Pack = smart_Plugs_Pack;
		Smart_Bulbs_Pack = smart_Bulbs_Pack;
		Wifi_Surveillance_Cameras = wifi_Surveillance_Cameras;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getDecoWifi_pack() {
		return DecoWifi_pack;
	}
	public void setDecoWifi_pack(int decoWifi_pack) {
		DecoWifi_pack = decoWifi_pack;
	}
	public int getWifi_Signal_Expansion() {
		return Wifi_Signal_Expansion;
	}
	public void setWifi_Signal_Expansion(int wifi_Signal_Expansion) {
		Wifi_Signal_Expansion = wifi_Signal_Expansion;
	}
	public int getSmart_Plugs_Pack() {
		return Smart_Plugs_Pack;
	}
	public void setSmart_Plugs_Pack(int smart_Plugs_Pack) {
		Smart_Plugs_Pack = smart_Plugs_Pack;
	}
	public int getSmart_Bulbs_Pack() {
		return Smart_Bulbs_Pack;
	}
	public void setSmart_Bulbs_Pack(int smart_Bulbs_Pack) {
		Smart_Bulbs_Pack = smart_Bulbs_Pack;
	}
	public int getWifi_Surveillance_Cameras() {
		return Wifi_Surveillance_Cameras;
	}
	public void setWifi_Surveillance_Cameras(int wifi_Surveillance_Cameras) {
		Wifi_Surveillance_Cameras = wifi_Surveillance_Cameras;
	}
}
