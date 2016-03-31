

from os import walk
import os

file_path = '~/Downloads/'
easy = 'easy.txt'
threshold = 'threshold.txt'
margin = 'margin.txt'


def prepare_files():
    f = []

    global file_path, threshold, margin, easy
    for (dirpath, dirnames, filenames) in walk(file_path):
        f.extend(filenames)
        break

    for file in f:
        if file.startswith('TSEasy'):
            os.rename(file_path + file, file_path + easy)
            print('easy there')

        if file.startswith('TSSpecial'):
            os.rename(file_path + file, file_path + margin)
            print('margin there')

        if file.startswith('TSThreshold'):
            os.rename(file_path + file, file_path + threshold)
            print('threshold there')


def get_easy_borrow():
    global file_path, easy
    easy_list = set()

    with open(file_path + easy, mode='r') as file_list:
        for line in file_list:

            if line.startswith(';'):
                continue
            else:
                easy_list.add(line.strip())
                # print(line.strip())


def get_margin_requirements():
    global file_path, margin
    margin_list = {}
    ticker = ''
    percentage = ''

    with open(file_path + margin, mode='r') as file_list:
        for line in file_list:

            if line.startswith(';'):
                continue
            else:
                temp = line.strip().split('|')

                if len(temp) > 1:
                    # print(temp[0] + ' ---- ' + temp[1])
                    margin_list[temp[0]] = temp[1]


def get_threshold_securities():
    global file_path, threshold
    threshold_list = set()

    with open(file_path + threshold, mode='r') as file_list:
        for line in file_list:

            if line.startswith(';'):
                continue
            else:
                threshold_list.add(line.strip())
                # print(line.strip())

    return threshold_list



if __name__ == '__main__':
    prepare_files()
    get_easy_borrow()
    get_margin_requirements()
    get_threshold_securities()
