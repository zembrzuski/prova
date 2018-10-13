import logging
import os

import tornado.ioloop
import web_app as web_app
from utils.yaml_loader import load_config
from elasticsearch import Elasticsearch


if __name__ == '__main__':
    config = load_config(os.getenv('config_path', 'config/local.yml'))

    logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.INFO  )
    logging.info(config)
    logging.info(config['elasticsearch']['hosts'])

    elasticsearch = Elasticsearch(config['elasticsearch']['hosts'])

    app = web_app.create_web_app(elasticsearch)

    logging.info('Backend service listening on 8888')
    app.listen(8888)
    tornado.ioloop.IOLoop.current().start()
