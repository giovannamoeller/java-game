public class Vetor
{
    public double x, y;

    Vetor()
    {
        x = 0;
        y = 0;
    }

    Vetor(double px, double py)
    {
        x = px;
        y = py;
    }

    Vetor mirrorHor()
    {
        this.y *= -1;
        return this;
    }
    
    Vetor mirrorVer()
    {
        this.x *= -1;
        return this;
    }

	public Vetor randomVector(int limit)
	{
        limit %= 360;
        limit = (limit != 0) ? limit : 360;
		
        double theta = Math.toRadians((int) (Math.random() * limit));

        x = Math.sin(theta);
        y = Math.cos(theta);

        return this;
	}

    public Vetor randomVector(){ return randomVector(360); }

	public Vetor rotateVector(int graus)
	{
        graus %= 360;
        
        if(graus == 0){
            return this;
        }
        else if(graus == 180){
            return this.mirrorHor().mirrorVer();
        }
        
        Vetor novo = new Vetor();
        double theta = Math.toRadians(graus);
        
        novo.x = this.x * Math.cos(theta) - this.y * Math.sin(theta);
        novo.y = this.x * Math.sin(theta) + this.y * Math.cos(theta);

        this.x = novo.x;
        this.y = novo.y;

        return this;
	}
}