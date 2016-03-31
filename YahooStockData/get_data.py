

import urllib2
# from Tkinter import *
from key_statistics import YahooKeyStatistics


ticker = "AAPL"
parser = YahooKeyStatistics()
yahoo = urllib2.urlopen('http://finance.yahoo.com/q/ks?s='+ticker+'+Key+Statistics')
data = parser.feed(yahoo.read().decode('utf-8'))
parser.print_key_size()
key_stats = parser.build_stat_data()




# if __name__ == '__main__':
#
#     # app = QApplication(sys.argv)
#     # ex = Example()
    # sys.exit(app.exec_())

# root = Tk()
# root.title("Stock Info")
# label = Label(root, text='Shares Short: ')
# label2 = Label(root, text=key_stats['Shares Short'])
# label.pack(side='left')
# label2.pack(side='right')
# print('*' + str(key_stats.keys()) + '*')
# counter_label(label)
# button = Button(root, text='Stop', width=25, command=root.destroy)
# button.pack()
#
# root.mainloop()