package com.paradorlarenta.pedidos.conexion;


import com.paradorlarenta.pedidos.models.FiltroModel;
import com.paradorlarenta.pedidos.models.ProductoModel;
import com.paradorlarenta.pedidos.models.RegistrarModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by INFO24 on  8/03/2018.
 */

public interface SOService {


    @GET(ApiUtils.GET_FILTROS)
    Call<List<FiltroModel>> ApiGetFiltros();

    @GET(ApiUtils.GET_PRODUCTOS)
    Call<List<ProductoModel>> ApiGetProductos(@Query("filtro") String filtro);

    @POST(ApiUtils.POST_REGISTRAR)
    Call<Void>ApiRegistrarPedido (@Body RegistrarModel registrarModel );


    /*
    @POST(ApiUtils.REGISTRO_URL)
    Call<BasicRequestModel> Registro(@Body RegistroModel registroModel);


    @POST(ApiUtils.AUTHENTICATION_URL)
    @FormUrlEncoded
    Call<UsuarioDJModel> Authentication(@Field("tUserName") String tUserName,
                                        @Field("tPassword") String tPassword,
                                        @Field("tTokenDispositivo") String tTokenDispositivo);

    @POST(ApiUtils.RECUPERAR_PASS_URL)
    @FormUrlEncoded
    Call<BasicRequestModel> RecuperaraPass(@Field("tUserName") String tUserName,
                                           @Field("tPassword") String tPassword,
                                           @Field("tTokenDispositivo") String tTokenDispositivo);


    @POST(ApiUtils.ACTUALIZAR_USUARIO_URL)
    Call<UsuarioDJModel> ActualizarUsuario(@Header("token") String token,
                                           @Body UsuarioDJModel usuarioDJModel);

    @POST(ApiUtils.ACTUALIZAR_TOKEN_DISPOSITIVO_URL)
    @FormUrlEncoded
    Call<BasicRequestModel> ActualizarTokenDispositivo(@Header("token") String token,
                                                       @Field("tUserName") String tUserName,
                                                       @Field("tPassword") String tPassword,
                                                       @Field("tTokenDispositivo") String tTokenDispositivo);


    @GET(ApiUtils.SOLICITUDES_ESTADO_NOTIFICADO_ACEPTADO)
    Call<RequestSolicitudModel> SolicitudesEstadoNotificadoAceptado(@Header("token") String token);

    @GET(ApiUtils.SOLICITUDES_ASIGNADAS)
    Call<RequestSolicitudModel> SolicitudesAsignadas(@Header("token") String token);

    @POST(ApiUtils.ACEPTAR_SOLICITUD)
    Call<BasicRequestModel> AceptarSolicitud(@Header("token") String token,
                                             @Body AceptacionModel aceptacionModel);

    @POST(ApiUtils.ACTUALIZAR_NOTIFICACION)
    Call<BasicRequestModel> ActualizarNotificacion(@Header("token") String token,
                                                   @Body BasicRequestModel basicRequestModel);


    @POST(ApiUtils.CUMPLIR_SOLICITUD)
    @Multipart
    Call<BasicRequestModel> CumplirSolicitud(@Header("token") String token,
                                             @Part("iIDDJSolicitudes") RequestBody requestBody,
                                             @Part List<MultipartBody.Part> files);


    @GET(ApiUtils.HISTORIAL_SOLICITUDES)
    Call<RequestSolicitudModel> HistorialSolicitudes(@Header("token") String token);


    @POST(ApiUtils.CANCELAR_SOLICITUD)
    Call<BasicRequestModel> CancelarSolicitud(@Header("token") String token,
                                              @Body Integer iIDSolicitud);

    @GET(ApiUtils.NOMBRES_FUENTES_EXTERNAS)
    Call<RequestFuenteExternaModel> NombreFuentesExternas(@Header("token") String token);

    /*
    @GET(ApiUtils.GET_USER_DATA_LOGIN)
    Call<LoginModel> GetUserDataLogin(@Header("token") String token);

    @GET(ApiUtils.GET_CASOS_ENTIDAD)
    Call<GetCasosEntidadModel> GetCasosEntidad(@Header("token") String token,
                                               @Path(value = "iIDEntidadEnc", encoded = true) String iIDEntidadEnc,
                                               @Path(value = "iIDCasoEnc", encoded = true) String iIDCasoEnc);
*/

}
