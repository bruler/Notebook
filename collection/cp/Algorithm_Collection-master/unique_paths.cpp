class Solution {
public:
int uniquePaths(int m, int n) {
        if ((m <= 0)  || (n <= 0)) return 0;
        vector<vector<int> > res(m, vector<int> (n));
        for(int i = 0; i < m; i ++ ){
            res[i][0] = 1;
        }
        for(int j = 0; j < n; j ++ ){
            res[0][j] = 1;
        }       
        for (int i=1; i<m; i++ ) {
            for (int j=1; j<n; j++) {
               res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        return res[m-1][n-1];
        
}


    
};



class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int> > &obstacleGrid) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function
        int m = obstacleGrid.size();
        int n = obstacleGrid[0].size();
         
        vector<vector<int> > arr(m,vector<int>(n,0));
         
        if (obstacleGrid[0][0]==1){return 0;}
        arr[0][0]=1;
        for (int i=1;i<m;i++){
            if (obstacleGrid[i][0]!=1){
                arr[i][0] = arr[i-1][0];
            }
        }
        for (int i=1;i<n;i++){
            if (obstacleGrid[0][i]!=1){
                arr[0][i] = arr[0][i-1];
            }
        }
        for (int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if (obstacleGrid[i][j]!=1){
                    arr[i][j] = arr[i][j-1] + arr[i-1][j];
                }
            }
        }  
        return arr[m-1][n-1];
    }
};
