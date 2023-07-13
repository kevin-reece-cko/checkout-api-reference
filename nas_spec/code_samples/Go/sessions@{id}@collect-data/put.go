// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/sessions/channels"
)

// API Keys
api, err := checkout.
			Builder().
			StaticKeys().
			WithSecretKey("secret_key").
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

// OAuth
api, err := checkout.
			Builder().
			OAuth().
			WithClientCredentials("client_id", "client_secret").
			WithScopes([]string{configuration.SessionsApp, configuration.SessionsBrowser}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

channel := channels.NewBrowserSession()
channel.AcceptHeader = "Accept:  *.*, q=0.1"
channel.JavaEnabled = true
channel.Language = "FR-fr"
channel.ColorDepth = "16"
channel.ScreenWidth = "1920"
channel.ScreenHeight = "1080"
channel.Timezone = "60"
channel.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36"
channel.ThreeDsMethodCompletion = common.Y
channel.IpAddress = "1.12.123.255"

response, err := api.Sessions.UpdateSession("session_id", channel,"session_secret") // "session_secret" is optional
if err != nil {
	return nil, err
}

return response, nil
