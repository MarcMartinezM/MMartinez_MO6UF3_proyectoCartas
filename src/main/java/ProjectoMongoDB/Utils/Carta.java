package ProjectoMongoDB.Utils;

public class Carta {
		private int id;
		private String type;
		private String nombre_Carta;
		private int costCarta;
		private int atkCarta;
		private int hpCarta;
		private String skill;
		private String faccion;
		public Carta() {
			super();
		}
		
		public Carta(int id, String type, String nombre_Carta, int costCarta, int atkCarta, int hpCarta, String skill,
				String faccion) {
			super();
			this.id = id;
			this.type = type;
			this.nombre_Carta = nombre_Carta;
			this.costCarta = costCarta;
			this.atkCarta = atkCarta;
			this.hpCarta = hpCarta;
			this.skill = skill;
			this.faccion = faccion;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre_Carta() {
			return nombre_Carta;
		}
		public void setNombre_Carta(String nombre_Carta) {
			this.nombre_Carta = nombre_Carta;
		}
		public int getCostCarta() {
			return costCarta;
		}
		public void setCostCarta(int costCarta) {
			this.costCarta = costCarta;
		}
		public int getAtkCarta() {
			return atkCarta;
		}
		public void setAtkCarta(int atkCarta) {
			this.atkCarta = atkCarta;
		}
		public int getHpCarta() {
			return hpCarta;
		}
		public void setHpCarta(int hpCarta) {
			this.hpCarta = hpCarta;
		}
		public String getSkill() {
			return skill;
		}
		public void setSkill(String skill) {
			this.skill = skill;
		}
		public String getFaccion() {
			return faccion;
		}
		public void setFaccion(String faccion) {
			this.faccion = faccion;
		}

		@Override
		public String toString() {
			return "Carta [id=" + id + ", type=" + type + ", nombre_Carta=" + nombre_Carta + ", costCarta=" + costCarta
					+ ", atkCarta=" + atkCarta + ", hpCarta=" + hpCarta + ", skill=" + skill + ", faccion=" + faccion
					+ "]";
		}
		
}
