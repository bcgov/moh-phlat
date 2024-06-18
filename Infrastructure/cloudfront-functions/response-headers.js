function handler(event) {
    var response = event.response;
    var headers = response.headers;
    console.log("Original Headers: ", JSON.stringify(headers));
    // values as recommended by https://content-security-policy.com/ section "Starter Policy"
    headers['content-security-policy'] = {
             value: "default-src 'self' https://common-logon-dev.hlth.gov.bc.ca https://common-logon-test.hlth.gov.bc.ca https://common-logon.hlth.gov.bc.ca; img-src 'self'; connect-src 'self'; style-src 'self'; script-src 'self'; base-uri 'self'; form-action 'self'; frame-ancestors 'self'"
    };

    console.log("Modified Headers: ", JSON.stringify(headers));
    return response;
}