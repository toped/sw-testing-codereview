

from StockAnalysis.historical_bars import TickerData
from StockAnalysis.historical_bars import BarData

data = TickerData('FB', 'D', 'NASDAQ')
gap_ups = []


def identify_gap_ups(percentage):
    i = 0
    for current_bar in data.candlesticks:
        # b.print_bar_data()
        if i > 1:
            last_bar = data.candlesticks[i-1]
            try:
                if current_bar.open > last_bar.close:
                    diff = float(current_bar.open) - float(last_bar.close)
                    calc_percent = diff / float(last_bar.close)
                    if calc_percent > .03:
                        print(current_bar.open + ' ' + last_bar.close + ' Date: '+current_bar.bar_datetime
                                + ' {0:.4f}'.format(calc_percent))
                        gap_ups.append(current_bar)

        i += 1


if __name__ == '__main__':
    identify_gap_ups(4)

    # for bar_data in gap_ups:
    #     print(bar_data.bar_datetime)