 // Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>
#include <queue>   

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;
 

    
using std::pair;
using std::priority_queue;     
using std::vector;
using namespace std;
struct Compare {    
  bool operator()(const pair<int, int>& lhs, const pair<int, int>& rhs) {
    return lhs.first > rhs.first;   
  }
};

vector<int> merge_arrays(const vector<vector<int>>& S) {
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> min_heap;
  vector<int> S_idx(S.size(), 0); 

  // Every array in S puts its smallest element in heap.
  for (int i = 0; i < S.size(); ++i) {
    if (S[i].size() > 0) {     
      min_heap.emplace(S[i][0], i);   
      S_idx[i] = 1;
    }
  }

  vector<int> ret;    
  while (!min_heap.empty()) {  
    pair<int, int> p = min_heap.top();
    ret.emplace_back(p.first); 
    // Add the smallest element into heap if possible.
    if (S_idx[p.second] < S[p.second].size()) {
      min_heap.emplace(S[p.second][S_idx[p.second]++], p.second);
    }
    min_heap.pop();    
  }
  return ret;
}

// @include
vector<int> sort_k_increasing_decreasing_array(const vector<int>& A) {
  // Decompose A into a set of sorted arrays.
  vector<vector<int>> S;
  bool is_increasing = true;  // the trend we are looking for.
  int start_idx = 0;
  for (int i = 1; i < A.size(); ++i) {
    if ((A[i - 1] < A[i] && !is_increasing) ||
        (A[i - 1] >= A[i] && is_increasing)) {
      if (is_increasing) {
        S.emplace_back(A.cbegin() + start_idx, A.cbegin() + i);
      } else {
        S.emplace_back(A.crbegin() + A.size() - i,
                       A.crbegin() + A.size() - start_idx);
      }
      start_idx = i;
      is_increasing = !is_increasing;  // inverse the trend.
    }
  }
  if (start_idx < A.size()) {
    if (is_increasing) {
      S.emplace_back(A.cbegin() + start_idx, A.cend());
    } else {
      S.emplace_back(A.crbegin(), A.crbegin() + A.size() - start_idx);
    }
  }

  return merge_arrays(S);
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    cout << "n = " << n << endl;
    uniform_int_distribution<int> pos_or_neg(0, 1);
    uniform_int_distribution<int> dis(0, 999999);
    for (size_t i = 0; i < n; ++i) {
      A.emplace_back(((pos_or_neg(gen)) ? 1 : -1) * dis(gen));
    }
    vector<int> ans = sort_k_increasing_decreasing_array(A);
    /*
    copy(A.begin(), A.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    */
    assert(ans.size() == A.size());
    assert(is_sorted(A.cbegin(), A.cend()));
  }
  return 0;
}

