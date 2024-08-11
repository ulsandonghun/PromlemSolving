package 이코테.DynamicPrograming;

import java.util.Arrays;
import java.util.Scanner;

public class 효율적인화폐구성2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();
        int[] coin = new int[N];
        int[] dp = new int[M+1];
        Arrays.fill(dp,-1);

        for (int i = 0; i < N; i++) {
            coin[i]=s.nextInt();
        }
        Arrays.sort(coin);
        int max = coin[N - 1];
        System.out.println(max);
        int min = coin[0];
        System.out.println(min);

        for (int i = min; i <= max; i++) { //Array outofBound 애러 방지.
            for (int coin_v : coin) {
                if (i % coin_v == 0) {
                    dp[i]=i/coin_v;
                }
            }
        }

        for (int i = max; i <= M; i++) {
            //만약 해당 i원이 화폐단위로 나누어 떨어지면 나눈값을 저장(오름차순이므로 순차적으로 나눠도 가장 마지막 큰값이 나눠짐.)
            for (int coin_value : coin) {
                if (i % coin_value==0) {
                    dp[i]=i/coin_value;
                }
            }

            for (int coin_value : coin) {
                //만약 i원에서 화폐단위를 뺀 값(원) 의 갯수가 존재한다면 대소비교
                if (dp[i - coin_value] != -1 && dp[i] != -1) {

                    dp[i] = Math.min(dp[i],dp[i-coin_value]+1 );
                } else if (dp[i - coin_value] != -1 && dp[i] == -1) {
                    dp[i] = dp[i - coin_value] + 1;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[M]);



    }
}
