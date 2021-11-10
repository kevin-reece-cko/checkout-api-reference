import re
import os

filename = "cko_collection_beta.json"

prefixes = ['pay_', 'bat_', 'cus_', 'sid_', 'evt_', 'ntf_', 'src_', 'tok_', 'act_', 'ras_', 'pl_', 'wh_']

placeholders = ['<PAYMENT_ID>', '<BATCH_ID>', '<CUSTOMER_ID>', '<EVENT_ID>', '<EVENT_NOTIFICATION_ID', '<SOURCE_ID', '<TOKEN_ID>', '<ACTION_ID>', '<ASSESSMENT_ID>', '<PAYMENT_LINK_ID>', '<WEBHOOK_ID>']

zip_iterator = zip(prefixes, placeholders)

replacement_dict = dict(zip_iterator)

postman_prefixes = open(filename, "r")
postman_placeholders = open(filename+'.tmp', "w")

text = postman_prefixes.read()
for i,j in replacement_dict.items():
    postman_prefixes.seek(0)
    text = re.sub(rf"{i}.[a-zA-Z0-9_]*", f"{j}", text)
postman_placeholders.write(text)

os.rename(filename+'.tmp', filename)
