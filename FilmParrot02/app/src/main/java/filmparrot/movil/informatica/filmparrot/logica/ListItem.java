package filmparrot.movil.informatica.filmparrot.logica;


public class ListItem {

        private int image;
        private String title;
        private double points;

        public ListItem() {
            super();
        }

        public ListItem(int image, String title, double points) {
            super();
            this.image = image;
            this.title = title;
            this.points = points;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPoints() {
        return points;
    }
}
