package ir.ac.kntu;

public enum BoardCell {
    EMPTY{
        @Override
        public String getColor() {
            return "white";
        }
    }, START{
        @Override
        public String getColor() {
            return "blue";
        }
    }, END{
        @Override
        public String getColor() {
            return "red";
        }
    }, OBSTACLE{
        @Override
        public String getColor() {
            return "black";
        }
    };

    public abstract String getColor();
}
