using System.Web.Http;
using System.Net.Http.Headers;

namespace Amigos
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

            // Para que WebAPI use JSON
            config.Formatters.JsonFormatter.
                SupportedMediaTypes.Add(new MediaTypeHeaderValue("text/html"));
        }
    }
}