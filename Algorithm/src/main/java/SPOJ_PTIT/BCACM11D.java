package SPOJ_PTIT;/**
 * Created by N80 on 27/10/2016.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: Nhat Tran Van
 * @date: 27/10/2016
 */

/**
 * Use BFS algorithm for show this problem
 * what is BFS? Loang theo chieu rong
 * Start BFS : so ngto thu nhat(start)
 * end BFS : when we visited so ngto thu hai (end)
 */

public class BCACM11D {

    private static int nTest;
    private static int[] isPrime = new int[10100];
    private static Scanner scanner;
    private static int start, end;

    private static void init(){
        scanner = new Scanner(System.in);

        nTest = scanner.nextInt();

        for (int i=2; i<10000; ++i) isPrime[i] = 1;
        for (int i=2; i<10000; ++i){
            if (isPrime[i] == 1){
                for (int j=i+i; j<10000; j+=i){
                    isPrime[j] = 0;
                }
            }
        }
        //isPrime[i] == 1 : i is prime, something else i isn't prime
    }

    private static void solve(){
        start = scanner.nextInt();
        end = scanner.nextInt();

        if (start == end){
            System.out.println(0);
            return;
        }

        int[] visited = new int[10100];//visited[i] == 0 <==> not visit i
        for(int i=1000; i<=9999; ++i) visited[i] = 0;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        int ans = 0;//ans = answer;
        while (!queue.isEmpty()){
            int size = queue.size();
            ++ans;

            for (int _size = 0; _size<size; ++_size){
                int first = queue.poll();
                for (int i=1; i<=4; ++i){
                    for (int j=0; j<=9; ++j){
                        int temp = update(first, i, j);
                        if (temp > 999 && isPrime[temp] == 1 && visited[temp] == 0){
                            if (temp == end){
                                System.out.println(ans);
                                return;
                            }
                            visited[temp] = 1;
                            queue.add(temp);
                        }
                    }
                }
            }

//            System.out.println(queue); u can remove commend for show queue changed in one by one step
        }
    }


    //int temp = update(1234, 2, 6); return temp = 1634
    public static int update(int item, int position, int value){

        int[] a = new int[5];

        for (int i=4; i>=1; --i) {
            a[i] = item%10;
            item /= 10;
        }

        a[position] = value;

        for (int i=1; i<=4; ++i){
            item = item*10 + a[i];
        }

        return item;
    }

    public static void main(String[] args) {
        init();
        for (int i=1; i<=nTest; ++i){
            solve();
        }
    }
}
