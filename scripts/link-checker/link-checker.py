import re
import os
import requests
import sys
from requests.exceptions import ConnectionError, MissingSchema, InvalidSchema
from sys import platform
from bs4 import BeautifulSoup

BASE_DIR = os.path.join(os.path.dirname(__file__), '..')

ignored_urls = []


def find_links(swagger_file: str):
    if swagger_file == "abc":
        swagger_file = f"{BASE_DIR}/../web_deploy/swagger.yaml"
        print("Checking for ABC broken links in ".upper() + swagger_file, "-----------------", sep="\n")
    elif swagger_file == "nas":
        swagger_file = f"{BASE_DIR}/../web_deploy/preview/crusoe/swagger.yaml"
        print("Checking for NAS broken links in ".upper() + swagger_file, "-----------------", sep="\n")

    broken_links_exist = False
    broken_link_array = []
    contents = open(swagger_file, 'r')
    soup = BeautifulSoup(contents, features="html.parser")
    links = [a['href'] for a in soup.findAll("a")]
    ignore_patterns = ["mailto", "#"]

    combined_regex = "".join([f"(?=^((?!^{combination}).)*$)" for combination in ignore_patterns])
    filter_regex = [filtered_links for filtered_links in links if
                    filtered_links not in ignored_urls and re.compile(combined_regex).match(filtered_links)]

    for check_broken_link in filter_regex:
        try:
            response = requests.get(check_broken_link)
        except (ConnectionError, MissingSchema, InvalidSchema):
            broken_link_array.append(check_broken_link)
            print(check_broken_link)
            broken_links_exist = True
        else:
            if response.status_code >= 400 and response.status_code != 999 and response.status_code != 429:
                broken_link_array.append(check_broken_link)
                print(("Broken link found: ").upper() + check_broken_link)

    if broken_links_exist and not platform == "darwin":
        print("Broken link(s) found. Please run this script locally to fix broken links!".upper())
        sys.exit(1)

    print("-----------------", sep="\n")
    return broken_link_array


find_links("abc")
find_links("nas")
