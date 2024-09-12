
public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    private static double G = 6.67E-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet b) {
        b.xxPos = xxPos;
        b.yyPos = yyPos;
        b.xxVel = xxVel;
        b.yyVel = yyVel;
        b.mass = mass;
        b.imgFileName = imgFileName;
    }

    public double calcDistance(Planet b) {
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }

    public double calcForceExertedBy(Planet b) {
        return G * this.mass * b.mass / (this.calcDistance(b) * this.calcDistance(b));
    }

    public double calcForceExertedByX(Planet b) {
        return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Planet b) {
        return calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double NetForceExertedByX = 0;
        for (Planet x : allPlanets) {
            if (!this.equals(x))
                NetForceExertedByX += this.calcForceExertedByX(x);
        }
        return NetForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double NetForceExertedByY = 0;
        for (Planet x : allPlanets) {
            if (!this.equals(x))
                NetForceExertedByY += this.calcForceExertedByY(x);
        }
        return NetForceExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel += dt * fX / this.mass;
        this.yyVel += dt * fY / this.mass;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
    }
}
