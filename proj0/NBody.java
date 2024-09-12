public class NBody {
    public static double readRadius(String universe) {
        In in = new In(universe);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String PlanetName) {
        Planet[] Planets = new Planet[5];
        In in = new In(PlanetName);
        in.readInt();
        in.readDouble();
        for (int i = 0; i < Planets.length && !in.isEmpty(); i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        }
        return Planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt =Double.parseDouble(args[1]);
        String filename = args[2];
        Planet [] Planets = readPlanets(filename);
        double Radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        for(int i= 0; i<= T;i+=1000) {
            StdDraw.clear();
            StdDraw.picture(0,0,"./images/starfield.jpg");
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int j = 0; j < 5; j++) {
                xForces[j] = Planets[j].calcNetForceExertedByX(Planets);
                yForces[j] = Planets[j].calcNetForceExertedByY(Planets);
                Planets[j].update(dt,xForces[j],yForces[j]);
                StdDraw.setScale(-Radius,Radius);
                for(Planet x : Planets){
                    x.draw();
                }
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (Planet planet : Planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }

    }

}
