package controleur;
public class Transport {
    private int idt, idv;
    private String type_transport;
    private float tarif_transport;
public Transport(
    int idt, int idv, String type_transport, float tarif_transport
)
{
    this.idt = idt;
    this.idv= idv;
    this.type_transport = type_transport;
    this.tarif_transport = tarif_transport;
}
public int getIdv() {
    return idv;
}
public void setIdv(int idv) {
    this.idv = idv;
}
public int getIdt() {
    return idv;
}
public void setIdt(int idt) {
    this.idt = idt;
}
public String getTypeTransport() {
    return type_transport;
}
public void setTypeTransport(String type_transport) {
    this.type_transport = type_transport;
}
public float getTarifTransport() {
    return tarif_transport;
}
public void setTarifTransport(Float tarif_transport) {
    this.tarif_transport = tarif_transport;
}
}










