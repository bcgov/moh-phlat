function handler(event) {
    var response = event.response;
    var headers = response.headers;

    // values as recommended by https://content-security-policy.com/ section "Starter Policy"
    headers['content-security-policy'] = [{
        key: 'Content-Security-Policy',
        value: "default-src 'self'; img-src 'self'; connect-src 'self'; style-src 'self'; script-src 'self'; base-uri 'self'; form-action 'self'"
    }];
    return response;
}