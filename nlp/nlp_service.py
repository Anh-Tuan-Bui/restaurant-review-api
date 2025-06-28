from flask import Flask, request, jsonify
from transformers import pipeline

app = Flask(__name__)
classifier = pipeline("sentiment-analysis")

@app.route('/analyze', methods=['POST'])
def analyze_sentiment():
    data = request.get_json()
    content = data.get('content', "")

    if not content:
        return jsonify({"error": "Missing content"}), 400
    
    result = classifier(content)[0]
    sentiment = result['label'].upper()

    return jsonify({"sentiment": sentiment})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5050, debug=True)