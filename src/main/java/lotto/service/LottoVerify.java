package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLottoNumber;

import java.util.*;

public class LottoVerify {
    public Map<Rank, Integer> verify(List<Lotto> lottoBundle, WinningLottoNumber winningLottoNumber) {
        Map<Rank, Integer> rankIntegerCounts = initRankCount();
        List<Integer> winningNumbers = new ArrayList<>(winningLottoNumber.getNumbers());

        for (Lotto lotto : lottoBundle) {
            int matches = countMatches(lotto.getNumbers(), winningNumbers);
            boolean bonusMatch = lotto.getNumbers().contains(winningLottoNumber.getBonus());
            Rank rank = Rank.of(matches, bonusMatch);
            rankIntegerCounts.put(rank, rankIntegerCounts.get(rank) + 1);
        }

        return rankIntegerCounts;
    }

    public Map<Rank, Integer> initRankCount() {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }
        return map;
    }

    public int countMatches(List<Integer> lotto, List<Integer> winningNumbers) {
        int matchCount = 0;
        for (int number : lotto) if (winningNumbers.contains(number)) matchCount++;
        return matchCount;
    }
}
