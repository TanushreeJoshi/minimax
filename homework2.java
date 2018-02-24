import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class Result{
	int score;int i;int j;
	Result(int a,int b,int c)
	{
	score=a;i=b;j=c;	
	}
	
}
class Nodes{
	char[][] c; int n; int score; int depth,i,j;
	Nodes(char[][] t)
	{
		c=t;
	}
	Nodes(char[][]t,int no,int s)
	{
		c=t; n=no; score=s;
	}
	Nodes(char[][]t,int no,int s,int d)
	{
		c=t; n=no; score=s; depth=d;
	}
	
	@Override
	public boolean equals(Object o)
	
	{
		if (!(o instanceof Nodes))
			return false;
		Nodes at = (Nodes)o;
		char[][] a=at.c;
		char[][] b=this.c;
		int counter=0;
		for(int i=0;i<a.length;i++)
			for(int j=0;j<a.length;j++)
			{
				if(a[i][j]!=b[i][j]) {
					counter=1; break;
				}
			}
		
		if(counter==1) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		
		char[][] c=this.c;
	    if (c == null)
	        return 0;

	    int result = 1;
	    for(int i=0;i<c.length;i++)
			for(int j=0;j<c.length;j++)
	        result = 31 * result + c[i][j];

	    return result;
	}
	
}

public class homework2 {
static int MIN=Integer.MIN_VALUE,MAX=Integer.MAX_VALUE,i=-10,j=-10;
	static Set<Nodes> s= new HashSet<>();

	static Result minimax1( boolean maximizingPlayer,
            Nodes c, int alpha, int beta)
{
	if(c.depth==1)return new Result(c.score,c.i,c.j);
	
 char[][] c1=c.c; int n=c.n;
	List<Nodes> l = findBestMove(c1,n,maximizingPlayer,c.score,c.depth);
	if(!l.isEmpty())
	{  if (maximizingPlayer)
    {
        int best = MIN;
 
        for (Nodes n1 : l)
        {	
            int val = minimax1(
                              false, n1, alpha, beta).score;
            if(c.depth==0&&val>best) {
            	i=n1.i;j=n1.j;}
            best = Math.max(best, val);
           alpha = Math.max(alpha, best);
 
            // Alpha Beta Pruning
           if (beta <= alpha)
           {  break;}
        }
        return new Result(best,i,j);
    }
    else
    {
        int best = MAX;

        for (Nodes n1 : l)
        {
        		
            int val = minimax1( 
                              true, n1, alpha, beta).score;

            best = Math.min(best, val);
            beta = Math.min(beta, best);
 
            // Alpha Beta Pruning
           if (beta <= alpha)
           {  break;}
        }
        return new Result(best,i,j);
    }
	}
	else return new Result(c.score,c.i,c.j);
}
static Result minimax( boolean maximizingPlayer,
            Nodes c, int alpha, int beta,int d)
{
	if(System.currentTimeMillis()>=e1)return null;
	if(System.currentTimeMillis()>=end&&d!=1)
	return	minimax(true,n1,MIN,MAX,1);
   	
	if(c.depth==d)return new Result(c.score,c.i,c.j);
	
 char[][] c1=c.c; int n=c.n;
	List<Nodes> l = findBestMove(c1,n,maximizingPlayer,c.score,c.depth);
	if(!l.isEmpty())
	{  if (maximizingPlayer)
    {
        int best = MIN;
 
        for (Nodes n1 : l)
        {	
            int val = minimax(
                              false, n1, alpha, beta,d).score;
            if(c.depth==0&&val>best) {
            	i=n1.i;j=n1.j;}
            best = Math.max(best, val);
           alpha = Math.max(alpha, best);
 
            // Alpha Beta Pruning
           if (beta <= alpha)
           {  break;}
        }
        return new Result(best,i,j);
    }
    else
    {
        int best = MAX;

        for (Nodes n1 : l)
        {
        		
            int val = minimax( 
                              true, n1, alpha, beta,d).score;

            best = Math.min(best, val);
            beta = Math.min(beta, best);
 
            // Alpha Beta Pruning
           if (beta <= alpha)
           {  break;}
        }
        return new Result(best,i,j);
    }
	}
	else return new Result(c.score,c.i,c.j);
}
	
	
	static Nodes rearrange(char[][] s,int n,int score)
	{
		int next1=n-1,next2=n-1,next3=n-1; int counter=0;
		for(int i=0;i<n;i++)
		{
			next1=n-1;next2=n-1;counter=0;
		while(next1>=0&&s[next1][i]!='*')
			{next1--;}
		next2=next1;
		while(next2>=0&&s[next2][i]=='*')
			{next2--;//score++;
			}
		
		if(next2==0&&s[next2][i]=='*') {
		continue;}
		next3=next2;
		
		while(next3>=0&&s[next3][i]!='*')
			next3--;
		if(next3<0)counter=next2-next3;
		else counter=Math.abs(next3-next2);
		while(counter>0)
		{
			s[next1][i]=s[next2][i];
			s[next2][i]='*';
					next1--; next2--;
			counter--;
		}	
		}	

		return new Nodes(s,n,score);
	}
	
	
	static int sc=0;
	static char[][] generateInt(char[][] inter1,int n, int i,int j)
	{   
		char no =inter1[i][j]; int a=i,b=j; 
		int counter=0; 

		if(inter1[a][b]==no)
		{
			inter1[a][b]='*'; sc++;
		}
		if(i-1>=0)
		{if(inter1[i - 1][j] != no) counter++;}
		else counter++;
		if(i + 1 < n)
		{if(inter1[i + 1][j] != no) counter++;}
		else counter++;
		if(j-1>=0)
		{if(inter1[i][j-1] != no) counter++;}
		else counter++;
		if(j+1<n)
		{if(inter1[i][j+1] != no) counter++;}
		else counter++;

		if(counter==4)return inter1;


		a=i-1;
		if(a>=0&&inter1[a][b]==no)
		{
	
			 generateInt(inter1,n,a,b); 
		}

		a=i+1;
		if(a<n&&inter1[a][b]==no)
		{
			 generateInt(inter1,n,a,b); 
		}
		a=i;b=j-1;
		if(b>=0&&inter1[a][b]==no)
		{
		 generateInt(inter1,n,a,b); 
		}
		a=i;b=j+1;
		if(b<n&&inter1[a][b]==no)
		{
	generateInt(inter1,n,a,b); 

		}
		return inter1;

	}

	static List<Nodes> findBestMove(char[][] board,int n,boolean m,int pScore,int d)
	{
		
	s.clear();
    int depth=d+1;
	List<Nodes> l= new ArrayList<Nodes>() ;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				{sc=0;
				char[][] temp= new char[n][n];
				if(board[i][j]=='*')continue;
				for(int i1=0;i1<n;i1++)
					for(int j1=0;j1<n;j1++)
						{
						temp[i1][j1]=board[i1][j1];
						}
				
			 temp=generateInt(temp,n,i,j);
				if(temp==null)continue;
				Nodes nd=rearrange(temp,n,sc*sc);
				nd.depth=depth;
				nd.i=i;nd.j=j;
				if(m==true) {
					nd.score= pScore+nd.score;
				}
				else {
					
					nd.score= pScore-nd.score;
				}
				if(s.contains(nd)) continue;
				else {
					l.add(nd);
					s.add(nd);
				}
				}
		

		return l;
	}

	static float time=0;static double start =0,end=0,e1=0;
	static Nodes n1;static int d;
	public static void main(String[]args)
	{
		FileInputStream in =null;
		FileOutputStream out=null;
		int n=0,p=0;  
				try {
			in= new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");
			@SuppressWarnings("resource")
			BufferedReader colr = new BufferedReader(new InputStreamReader(in));
			@SuppressWarnings("resource")
			PrintStream pw = new PrintStream(out);
			String s;
			String[] line = new String[3];
			int i1=0;
			while(i1<3){
				line[i1] = colr.readLine();
				i1++;
			}
			n=Integer.parseInt(line[0]);
			p=Integer.parseInt(line[1]);
			time=Float.parseFloat(line[2]);
		 start = System.currentTimeMillis();
			    end = start + (time-0.5f)*1000;
			    e1=start+time*1000;
			String[] board= new String[n]; 
			i1=0; int counter=0;
			while((s = colr.readLine()) != null && counter<n){
				board[i1] = s;
				i1++;counter++;
			}
			char[][] state=new char[n][n];
			int k=0;
			for(int j=0;j<n;j++)
			{
				state[k++]=board[j].toCharArray();
			}
	
			
	 n1 = new Nodes(state,n,0,0);
	 Result r;
		if (time<0.5f)d=1;
		else d=3;
		//if (time<5f)r=minimax1(true,n1,MIN,MAX);
		//else
	 r=minimax(true,n1,MIN,MAX,d);
	 //r=minimax1(true,n1,MIN,MAX);
	 //r=minimax(true,n1,MIN,MAX,d);
		char a= (char) (r.j+'A'); int b= (1+r.i);
	System.out.print(a+""+b);
	pw.print(a);
	pw.println(b);
	char[][] res=generateInt(state,n,r.i,r.j);
	
	n1=rearrange(res,n,0);
	for(int k1=0;k1<n;k1++)
		{
		for(int l=0;l<n;l++)
		pw.print(n1.c[k1][l]);
	pw.println();
		}
	
	
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
}
