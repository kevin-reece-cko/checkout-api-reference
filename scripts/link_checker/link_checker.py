import re
import os
import requests
from requests.exceptions import ConnectionError, MissingSchema, InvalidSchema
from bs4 import BeautifulSoup

BASE_DIR = os.path.join(os.path.dirname(__file__), '..')
IGNORED_URLS = []
IGNORED_PATTERNS = ["mailto", "#"]


def check_broken_links(swagger_file: str):
    if swagger_file == "abc":
        swagger_file = f"{BASE_DIR}/../web_deploy/previous/swagger.yaml"
    elif swagger_file == "nas":
        swagger_file = f"{BASE_DIR}/../web_deploy/swagger.yaml"

    broken_link_array = []
    contents = open(swagger_file, 'r')
    soup = BeautifulSoup(contents, features="html.parser")
    raw_links = [a['href'] for a in soup.findAll("a")]

    combined_regex = "".join([f"(?=^((?!^{combination}).)*$)" for combination in IGNORED_PATTERNS])
    all_links = [filtered_links for filtered_links in raw_links if filtered_links not in IGNORED_URLS
                 and re.compile(combined_regex).match(filtered_links)]

    for i in all_links:
        try:
            response = requests.get(i)
        except (ConnectionError, MissingSchema, InvalidSchema):
            broken_link_array.append(i)
        else:
            if response.status_code >= 400 and response.status_code != 999 and response.status_code != 429:
                broken_link_array.append(i)

    return broken_link_array
