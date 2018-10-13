import tornado.web


class StatusHandler(tornado.web.RequestHandler):

    def initialize(self, *args, **kwargs):
        self.foo_bah = kwargs['foo']

    def get(self, *args, **kwargs):
        self.write({
            'status': 'Running',
            'foo': self.foo_bah
        })
