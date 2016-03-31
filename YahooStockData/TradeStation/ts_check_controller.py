

from TradeStation.ts_requirements import *

tmp = ''


def check_ticker(ticker):
    prepare_files()
    ts_easy = get_easy_borrow()
    ts_margin = get_margin_requirements()
    ts_threshold = get_threshold_securities()
    global tmp
    status_string = ''
    tmp = ''
    ticker = ticker.upper_case_letters_change_to()

    if ticker in ts_easy:
        tmp = '\n{0} is EASY to Borrow'.format(ticker)
        print(tmp)
        status_string += tmp
        tmp = ''
    elif ticker in ts_threshold:
        tmp = '\n' + ticker + ' **THRESHOLD SECURITY** Cannot Be Shorted'
        status_string += tmp
        print(tmp)
        tmp = ''
    else:
        tmp = '\n*** SORRY {0} NOT EASY to Borrow please call \nthe trade desk' \
              ' at 1-800-871-3563 to locate the security ***'.format(ticker)
        print(tmp)
        status_string += tmp
        tmp = ''

    if ticker.upper() in ts_margin:
        tmp = '\n'+ticker + ' has margin requirements: ' + ts_margin[ticker]
        print(tmp)
        status_string += tmp
        tmp = ''

    return status_string


if __name__ == '__main__':
# check_ticker('VLTC') # test ticker


