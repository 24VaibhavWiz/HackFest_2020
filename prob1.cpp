#include <bits/stdc++.h>
 
using namespace std;
 
int maximumPower(string s)
{
 
    int size = s.length();
    int zeroesCount = 0, maxZeroesCount = 0;

    for(int i=0; i<size; i++)
   {
        if(s[i]=='0')
	{
            zeroesCount++;
        }
	
	else
	{
            maxZeroesCount = max(maxZeroesCount, zeroesCount);
            zeroesCount = 0;
        }
    }
    
    maxZeroesCount = max(maxZeroesCount, zeroesCount);

    for(int i=0; s[i]=='0'; i++) 
        zeroesCount++;

    maxZeroesCount = max(maxZeroesCount, zeroesCount);
 
    if(maxZeroesCount>=size)
        return -1;
 
    else if(maxZeroesCount==0) 
        return 0;
    
    return maxZeroesCount;
}
 

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));
 
    string s;
    getline(cin, s);
 
    int result = maximumPower(s);
 
    fout << result << "\n";
 
    fout.close();
 
    return 0;
}
