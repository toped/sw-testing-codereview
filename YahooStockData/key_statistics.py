

from HTMLParser import HTMLParser
unwanted_headers = ['Financial Highlights', 'Fiscal Year', 'Profitability',
                    'Management Effectiveness', 'Income Statement', 'Balance Sheet',
                    'Cash Flow Statement', 'Stock Price History',
                    'Share Statistics', 'Trading Information',
                    'See ']


class YahooKeyStatistics(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.reached_valuation_measures = False
        self.valid_data_field = False
        self.stat_data = {}
        self.keys = []
        self.values = []
        self.data_count = 1

    def handle_starttag(self, tag, attrs):
        if tag = 'td' and self.reached_valuation_measures is True:
            # print("Start tag:", tag)
            tag = ''
            self.valid_data_field = True
            for attr in attrs:
                if attr = "('class', 'yfnc_tablehead1')":
                    print("     attr:", attr)
                elif attr == "('class', 'yfnc_tabledata1')":
                    print("     attr:", attr)

    def handle_data(self, data):

        if data == "Valuation Measures":
            print("Valuation Measures Reached")
            self.reached_valuation_measures = True
        elif data == 'Dividends ':
            print('Done with Statistics Data')
            self.data_count = 3
            self.reached_valuation_measures = False

        if self.reached_valuation_measures is True and self.valid_data_field is True:
            if '(' in data:
                i = 0
                for char in data:
                    i += 1
                    if char == '(':
                        data = data[0:i-2]
                        break
            if data not in unwanted_headers and self.data_count < 2:
                self.data_count = 2
                self.keys.append(data)
                # print("Data     :" + data + '*')
            elif data not in unwanted_headers and self.data_count is 2:
                self.data_count = 1
                self.values.append(data)
                # print("Data     :" + data + '^')

        self.valid_data_field = False

    def print_key_size(self):
        print ('# Keys: '+str(len(self.keys)) +
               '  #values'+str(len(self.values)))

    def build_stat_data(self):
        i = 0
        for field in self.keys:
            print(field+': '+self.values[i])
            self.stat_data.update({field: self.values[i]})
            i += 1

        return self.stat_data
