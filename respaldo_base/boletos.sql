--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bloque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bloque (
    idb integer NOT NULL,
    bloque json
);


ALTER TABLE public.bloque OWNER TO postgres;

--
-- Name: bloque_idb_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bloque_idb_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bloque_idb_seq OWNER TO postgres;

--
-- Name: bloque_idb_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bloque_idb_seq OWNED BY public.bloque.idb;


--
-- Name: permisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permisos (
    idp integer NOT NULL,
    idur integer NOT NULL,
    idmr integer NOT NULL,
    pms_permiso integer NOT NULL,
    pms_centro integer NOT NULL,
    pms_observacion character varying(220) DEFAULT NULL::character varying,
    aud_fecha date,
    aud_hora character(6) DEFAULT NULL::bpchar,
    aud_usuario integer,
    aud_centro integer,
    aud_estado integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.permisos OWNER TO postgres;

--
-- Name: permisos_idp_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permisos_idp_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permisos_idp_seq OWNER TO postgres;

--
-- Name: permisos_idp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permisos_idp_seq OWNED BY public.permisos.idp;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    idu integer NOT NULL,
    usr_nombre character varying(204) DEFAULT ''::character varying NOT NULL,
    usr_nick character varying(204) DEFAULT ''::character varying NOT NULL,
    usr_clave character varying(204) DEFAULT ''::character varying NOT NULL,
    tipo character varying(14) DEFAULT ''::character varying NOT NULL,
    aud_fecha date,
    aud_hora character(6) DEFAULT NULL::bpchar,
    aud_usuario integer,
    aud_centro integer,
    aud_estado integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_idu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_idu_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_idu_seq OWNER TO postgres;

--
-- Name: usuario_idu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_idu_seq OWNED BY public.usuario.idu;


--
-- Name: validarpermiso; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.validarpermiso AS
 SELECT u.idu,
    p.pms_centro,
    u.usr_nick,
    u.usr_clave,
    p.pms_permiso,
    p.idmr
   FROM public.permisos p,
    public.usuario u
  WHERE ((u.idu = p.idur) AND (u.aud_estado = 1) AND (p.aud_estado = 1) AND (p.idmr = 0));


ALTER TABLE public.validarpermiso OWNER TO postgres;

--
-- Name: vlistaboleto; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vlistaboleto AS
 SELECT (json_array_elements(((bloque.bloque -> 'dato'::text) -> 'boletosf'::text)) ->> 'nboleto'::text) AS nboleto,
    (json_array_elements(((bloque.bloque -> 'dato'::text) -> 'boletosf'::text)) ->> 'boleto'::text) AS boleto,
    (json_array_elements(((bloque.bloque -> 'dato'::text) -> 'boletosf'::text)) ->> 'hashb'::text) AS hashb,
    (json_array_elements(((bloque.bloque -> 'dato'::text) -> 'boletosf'::text)) ->> 'rutab'::text) AS rutab,
    (bloque.bloque ->> 'idbloque'::text) AS idbloque
   FROM public.bloque
  WHERE (((bloque.bloque -> 'dato'::text) ->> 'boletosf'::text) IS NOT NULL);


ALTER TABLE public.vlistaboleto OWNER TO postgres;

--
-- Name: bloque idb; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bloque ALTER COLUMN idb SET DEFAULT nextval('public.bloque_idb_seq'::regclass);


--
-- Name: permisos idp; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permisos ALTER COLUMN idp SET DEFAULT nextval('public.permisos_idp_seq'::regclass);


--
-- Name: usuario idu; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN idu SET DEFAULT nextval('public.usuario_idu_seq'::regclass);


--
-- Data for Name: bloque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bloque (idb, bloque) FROM stdin;
15	{"idbloque":12, "previousHash":"000008eb721623429f2a4dbf958096330d0650e795852984d0616d9502688817","actualhash":"00000f4ae5a262ea29c3c53ecf311a32f41869a017b6f6a7b77270759c105f69","timeStamp":1695064757405,"nonce":1708636,"dato":{"cod_boleto":20230918141917,"numero_boleto":4,"titulo":"aaaaa","premios":"wweee", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"22", "lugar":"ppppp","notas":"mmmm","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20230918141917", "hashb":"fc5f2c9bb2a992fb2146845e26dbb6954cd541d6c2973a1f8048c846a3f7b27b", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":2, "boleto":"20230918141917", "hashb":"b8e1b0739e13ed53eb1a37fce2a70cb605e1f0344b743fc5d9cd993cc40eb81a", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":3, "boleto":"20230918141917", "hashb":"c1bfc67ad62251071750ceaa39c6b2d12513609c57b6498d84e78fb892c814e4", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":4, "boleto":"20230918141917", "hashb":"ab3af84d1332c61d0031f82eb2eac0d7803567db652c1e403c3aa911da731a93", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"}]   } }
5	{"idbloque":3, "previousHash":"22222222","actualhash":"000000c248892c78f68e7c5688b3b2164b4db06bdee42ed52b53a2d79ac2724f","timeStamp":1694698947955,"nonce":1815360,"dato":{"cod_boleto": 6666661,"numero_boleto":2,"titulo":"aaa","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"eeee",  "lugar":"ddd","notas":"ffff","usuario":"2" } }
25	{"idbloque":22, "previousHash":"00000b958ffa9b0539839ea59343daaa2e7103b150669ed6969f84b295b65dea","actualhash":"00000ad06207234becf46757ee742855ea391b5d649aea8e85f7f707fa1eacfb","timeStamp":1697465802586,"nonce":1149151,"venta":{"idr_bloque":21,"nboleto":1,"boleto":"20231012153011","hashold":"0e2542e85c9a8c18c3388c8cebb691cca66c1de6de86a5e9b0150ff9cdcc9204", "rutaold":"boleto-20231012153011-00001.pdf","cedula":"111111","nombre":"adr", "telefono":"23343","direccion":"sbd","email":"ccddd","Fecha Compra":"2023-10-16-09-16-40","usuario":"1" ,  "hashnew":""    } }
11	{"idbloque":9, "previousHash":"00000f078a1066ea4ee7f2776d3113bc7c47575cb89bc00e84921a14d55fa489","actualhash":"00000d4bc516f4d90e590cac4ab65b72729cdd068e1b1fcd65738a44b7e7b614","timeStamp":1694791777160,"nonce":256441,"dato":{"cod_boleto":20230915102917,"numero_boleto":4,"titulo":"rifax","premios":"dat", "frifa":"2018-07-22","hora_rifa":"1234","valor":"2",  "lugar":"quiyo","notas":"nnnnn","usuario":"1" } }
13	{"idbloque":10, "previousHash":"00000d4bc516f4d90e590cac4ab65b72729cdd068e1b1fcd65738a44b7e7b614","actualhash":"000005afccc6456a662c413ea02e0184107a74e27bdf7966cde34888e0da9ba5","timeStamp":1695055173958,"nonce":1026520,"dato":{"cod_boleto":20230918113933,"numero_boleto":4,"titulo":"aaaa","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"2", "lugar":"ddddd","notas":"fffff","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20230918113933", "hashb":"043254ea1864e7da510e489b88911bb80ba238cb632e4c19954db9bbb19c1523", "rutab":"/home/admin/NetBeansProjects/docs/t1"},{"nboleto":2, "boleto":"20230918113933", "hashb":"f5c8660561ef37c0d15387176c1319d765464cbd6772f1dbf03a30c2505f9d22", "rutab":"/home/admin/NetBeansProjects/docs/t1"},{"nboleto":3, "boleto":"20230918113933", "hashb":"98109fe7ff1ef31c016a61deacc873e162437efeaa651e41c101f44bbb5a1646", "rutab":"/home/admin/NetBeansProjects/docs/t1"},{"nboleto":4, "boleto":"20230918113933", "hashb":"5618ff9343a70ba508620c9f0511fd66c71fcf4e6eb8d2aa84062b973ffb1cdf", "rutab":"/home/admin/NetBeansProjects/docs/t1"}]   } }
10	{"idbloque":8, "previousHash":"0000005ad566df5e70f8ea2a51b53e21738603f29daf9e0f10ea2d6ddbf8c873","actualhash":"00000f078a1066ea4ee7f2776d3113bc7c47575cb89bc00e84921a14d55fa489","timeStamp":1694709141932,"nonce":764153,"dato":{"cod_boleto":20230914113221,"numero_boleto":2,"titulo":"qqqq","premios":"wwww", "frifa":"2023-09-14","hora_rifa":"cccc","valor":"eeee",  "lugar":"rrrr","notas":"ddddd","usuario":"1" } }
3	{"idbloque":1,"previousHash":"0000000000000000000000000000000000","actualhash":"111111111","timeStamp":1111111,"nonce":111111,"datox":"bloque" }
4	{"idbloque":2,"previousHash":"111111111","actualhash":"22222222","timeStamp":2222222,"nonce":222222,"datox":"bloque 2" }
2	{"idbloque":0,"previousHash":"0000000000000000000000000000000000000000000000000000000000000000","actualhash":"0000000000000000000000000000000000000000000000000000000000000000","timeStamp":0,"nonce":0,"datox":"inicio de bloque" }
6	{"idbloque":4, "previousHash":"000000c248892c78f68e7c5688b3b2164b4db06bdee42ed52b53a2d79ac2724f","actualhash":"00000645a5c0af7bec934948273c3876d30426aa8a31e43577867f0ba14cc8b9","timeStamp":1694699467394,"nonce":89428,"dato":{"cod_boleto": 111111,"numero_boleto":6,"titulo":"aaaa","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"eeee",  "lugar":"dddd","notas":"gggggg","usuario":"1" } }
7	{"idbloque":5, "previousHash":"00000645a5c0af7bec934948273c3876d30426aa8a31e43577867f0ba14cc8b9","actualhash":"0000025a695e4153c51843b950ddcd30d535a03add888ebd08a6178995e22be8","timeStamp":1694702400466,"nonce":187002,"dato":{"cod_boleto": 222222,"numero_boleto":1,"titulo":"aaaa","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"eeee",  "lugar":"qqq","notas":"zzzzz","usuario":"1" } }
8	{"idbloque":6, "previousHash":"0000025a695e4153c51843b950ddcd30d535a03add888ebd08a6178995e22be8","actualhash":"0000091b72992260147a3936ebf276bfb02a66abde0d27cb59d2590128b85918","timeStamp":1694703390980,"nonce":496846,"dato":{"cod_boleto": 333333,"numero_boleto":1,"titulo":"aaaa","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"eeee",  "lugar":"dddd","notas":"eeee","usuario":"1" } }
9	{"idbloque":7, "previousHash":"0000091b72992260147a3936ebf276bfb02a66abde0d27cb59d2590128b85918","actualhash":"0000005ad566df5e70f8ea2a51b53e21738603f29daf9e0f10ea2d6ddbf8c873","timeStamp":1694703580690,"nonce":2129582,"dato":{"cod_boleto": 444444,"numero_boleto":2,"titulo":"Titulo","premios":"Premio", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"ssss",  "lugar":"Lugar","notas":"sssss","usuario":"1" } }
14	{"idbloque":11, "previousHash":"000005afccc6456a662c413ea02e0184107a74e27bdf7966cde34888e0da9ba5","actualhash":"000008eb721623429f2a4dbf958096330d0650e795852984d0616d9502688817","timeStamp":1695055735013,"nonce":1039241,"dato":{"cod_boleto":20230918114854,"numero_boleto":5,"titulo":"wwwwww","premios":"ssssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"2", "lugar":"dddd","notas":"ccccc","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20230918114854", "hashb":"d13043808ff758a60d641b2be387d6ff31b352ccea3b437eab1395ca51d10e46", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":2, "boleto":"20230918114854", "hashb":"7afa24acb3f6e569d3b2fe24a4376f3c0210a7b7307316082b38eda1ffb36fb3", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":3, "boleto":"20230918114854", "hashb":"3a89db30176a6c768d558ea85bda9faa9ca3bb8a588f044906be490759f373c1", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":4, "boleto":"20230918114854", "hashb":"1e2496083a528dc1c6c21ca34eec67acdbc763f7d9bffa99f6d2aef07aff13c2", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"},{"nboleto":5, "boleto":"20230918114854", "hashb":"46203a73894f9bb7686ba88d731b37e2b038d10636974f3e0119d9b91b6c3568", "rutab":"/home/admin/NetBeansProjects/boletos/web/archivos/t1"}]   } }
17	{"idbloque":14, "previousHash":"00000621b24bdcf1e429f9ed7a93da36ce25c054771207755806d3ac04abf8fc","actualhash":"000000bcbbe1e52a19396cd29cd913e7f7edc027bb8fad0f97a76bc94368a7df","timeStamp":1695231272116,"nonce":1109919,"dato":{"cod_boleto":20230920123431,"numero_boleto":4,"titulo":"eeee","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"1234","valor":"2", "lugar":"hhhh","notas":"zzzzzz","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20230920123431", "hashb":"8e36ea7b02c6a901fcaf37ea2d3136667b6d345a5c849efcb77f5d0f0f257646", "rutab":"boleto-20230920123431-00001.pdf"},{"nboleto":2, "boleto":"20230920123431", "hashb":"7e8d72eaf39fa676ab91a637d2b39df2dd758c96e607ca3dee607e8a1801cd43", "rutab":"boleto-20230920123431-00002.pdf"},{"nboleto":3, "boleto":"20230920123431", "hashb":"2ac384ceca889ff4560d593098dd682590496afed96a1b5d9dfb80481d73c1d8", "rutab":"boleto-20230920123431-00003.pdf"},{"nboleto":4, "boleto":"20230920123431", "hashb":"d3a0d8be78a9d99ca1efb33fd0b497f44ea1d7a82b2aca5eb64dca4b205fef19", "rutab":"boleto-20230920123431-00004.pdf"}]   } }
16	{"idbloque":13, "previousHash":"00000f4ae5a262ea29c3c53ecf311a32f41869a017b6f6a7b77270759c105f69","actualhash":"00000621b24bdcf1e429f9ed7a93da36ce25c054771207755806d3ac04abf8fc","timeStamp":1695149435756,"nonce":1894183,"dato":{"cod_boleto":20230919135035,"numero_boleto":4,"titulo":"aaaaaa","premios":"ssss", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"2", "lugar":"dddd","notas":"ffff","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20230919135035", "hashb":"62c0117e6a25e5bee00a140f7e9770d1648abf1a39bf06ce1de6e4b6e15e2de0", "rutab":"boleto-20230919135035-00001.pdf"},{"nboleto":2, "boleto":"20230919135035", "hashb":"4a8ff577269a90c2640e42c7376269c6dd645ce5275931ee7fb5598ce86dc0d3", "rutab":"boleto-20230919135035-00002.pdf"},{"nboleto":3, "boleto":"20230919135035", "hashb":"022d4617e21b432874cbfd766f39123d58d35091a2d9b39e727e4a0bdeac1d43", "rutab":"boleto-20230919135035-00003.pdf"},{"nboleto":4, "boleto":"20230919135035", "hashb":"170a79bdfa5c6428670e310809ea8a206cc1a918c84e3723c31acd230276dbf4", "rutab":"boleto-20230919135035-00004.pdf"}]   } }
18	{"idbloque":15, "previousHash":"000000bcbbe1e52a19396cd29cd913e7f7edc027bb8fad0f97a76bc94368a7df","actualhash":"000009656aaf5c9bebb75c6a1ab38ed4d845ba87497b639b49290a1126010632","timeStamp":1695415082293,"nonce":1043442,"dato":{"cod_boleto":20230922153801,"numero_boleto":4,"titulo":"dddd","premios":"pppp", "frifa":"2018-07-22","hora_rifa":"cccc","valor":"2", "lugar":"zzzzz","notas":"nada","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20230922153801", "hashb":"3257782b03ee318bd170813afa450db628ef118d093cfe2554dee7a0f94fe106", "rutab":"boleto-20230922153801-00001.pdf"},{"nboleto":2, "boleto":"20230922153801", "hashb":"01e25bf5cf78a21c20f05f52bf0fbe6cc0cc72c07f0fbb7e6f384827bac62e62", "rutab":"boleto-20230922153801-00002.pdf"},{"nboleto":3, "boleto":"20230922153801", "hashb":"ab216028400a0d7cde25b37e90f976cad693c6fe7b7be5fdbaa3a32342ace921", "rutab":"boleto-20230922153801-00003.pdf"},{"nboleto":4, "boleto":"20230922153801", "hashb":"a156ca6a6bfd68d376469995dcb49e1b85617c7c93df99c274020325fbf9d5a2", "rutab":"boleto-20230922153801-00004.pdf"}]   } }
19	{"idbloque":16, "previousHash":"000009656aaf5c9bebb75c6a1ab38ed4d845ba87497b639b49290a1126010632","actualhash":"00000979a8d39ab5eb7dd03cbb5fe7fb41100f339b499aa17b6bf3597b8f64ba","timeStamp":1696508752841,"nonce":814941,"dato":{"cod_boleto":20231005072552,"numero_boleto":4,"titulo":"boletos","premios":"regreso", "frifa":"2018-07-22","hora_rifa":"2342","valor":"2", "lugar":"sang","notas":"mmmmm","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20231005072552", "hashb":"f272c1a743490a5f4ef3fb8cdf718e6e02ea2e51066807c721d4b8dc7c7f5e4c", "rutab":"boleto-20231005072552-00001.pdf"},{"nboleto":2, "boleto":"20231005072552", "hashb":"6a1988b9baed12866ff3019c034ea7ed4a61e469495a6eeebe97d165a276295f", "rutab":"boleto-20231005072552-00002.pdf"},{"nboleto":3, "boleto":"20231005072552", "hashb":"7c8519c70940de26c72643f5064064ae6908a96857a780393e1e92347c7c2ce3", "rutab":"boleto-20231005072552-00003.pdf"},{"nboleto":4, "boleto":"20231005072552", "hashb":"147fe9233a359aaceee89c0dfebc4eb26e729527c59e568759a0082799f7b38a", "rutab":"boleto-20231005072552-00004.pdf"}]   } }
20	{"idbloque":17, "previousHash":"00000979a8d39ab5eb7dd03cbb5fe7fb41100f339b499aa17b6bf3597b8f64ba","actualhash":"00000fd08e6e16b4112dd3d1c0225013235dde920b0b15c7d950143d382d5963","timeStamp":1696510814690,"nonce":1382526,"venta":{"idr_bloque":16,"nboleto":2,"boleto":"20231005072552","hashold":"6a1988b9baed12866ff3019c034ea7ed4a61e469495a6eeebe97d165a276295f", "rutaold":"boleto-20231005072552-00002.pdf","cedula":"mx","nombre":"adr", "telefono":"22233","direccion":"dv","email":"ssss","Fecha Compra":"2023-10-05-08-00-14","usuario":"1" ,  "hashnew":""    } }
21	{"idbloque":18, "previousHash":"00000fd08e6e16b4112dd3d1c0225013235dde920b0b15c7d950143d382d5963","actualhash":"000007fd16b3bc60a5e94a8c590e2b57e5dc5426a956d2a06d1c525cf505ba75","timeStamp":1696622531294,"nonce":620732,"venta":{"idr_bloque":16,"nboleto":4,"boleto":"20231005072552","hashold":"147fe9233a359aaceee89c0dfebc4eb26e729527c59e568759a0082799f7b38a", "rutaold":"boleto-20231005072552-00004.pdf","cedula":"ddd","nombre":"ddd", "telefono":"ddd","direccion":"ddd","email":"dddd","Fecha Compra":"2023-10-06-15-02-10","usuario":"1" ,  "hashnew":""    } }
22	{"idbloque":19, "previousHash":"000007fd16b3bc60a5e94a8c590e2b57e5dc5426a956d2a06d1c525cf505ba75","actualhash":"0000037e71093034e8f49d7c380afb2e0df58737888516cd3ec03790ea646615","timeStamp":1696969281997,"nonce":1236738,"venta":{"idr_bloque":16,"nboleto":3,"boleto":"20231005072552","hashold":"7c8519c70940de26c72643f5064064ae6908a96857a780393e1e92347c7c2ce3", "rutaold":"boleto-20231005072552-00003.pdf","cedula":"","nombre":"", "telefono":"","direccion":"","email":"","Fecha Compra":"2023-10-10-15-21-21","usuario":"1" ,  "hashnew":""    } }
23	{"idbloque":20, "previousHash":"0000037e71093034e8f49d7c380afb2e0df58737888516cd3ec03790ea646615","actualhash":"000002f0cfe9b4362ea5663543e9439f484dcc92bb071bdd1853694fcfa27dbe","timeStamp":1696969324357,"nonce":369145,"venta":{"idr_bloque":16,"nboleto":2,"boleto":"20231005072534","hashold":"6a1988b9baed12866ff3019c034ea7ed4a61e469495a6eeebe97d165a276295f", "rutaold":"boleto-20231005072552-00002.pdf","cedula":"1111111","nombre":"adr", "telefono":"33333","direccion":"vvvvvv","email":"zzzzz","Fecha Compra":"2023-10-10-15-22-04","usuario":"1" ,  "hashnew":""    } }
24	{"idbloque":21, "previousHash":"000002f0cfe9b4362ea5663543e9439f484dcc92bb071bdd1853694fcfa27dbe","actualhash":"00000b958ffa9b0539839ea59343daaa2e7103b150669ed6969f84b295b65dea","timeStamp":1697142613599,"nonce":1097942,"dato":{"cod_boleto":20231012153011,"numero_boleto":5,"titulo":"prueba","premios":"mayor", "frifa":"2018-07-22","hora_rifa":"1234","valor":"2", "lugar":"sangolqui","notas":"mmm","usuario":"1","boletosf":[{"nboleto":1, "boleto":"20231012153011", "hashb":"0e2542e85c9a8c18c3388c8cebb691cca66c1de6de86a5e9b0150ff9cdcc9204", "rutab":"boleto-20231012153011-00001.pdf"},{"nboleto":2, "boleto":"20231012153011", "hashb":"343eda6d95ba5dc1533d96ee08c4b89c3b867dd29a9784bc8de7cc0c91faa31a", "rutab":"boleto-20231012153011-00002.pdf"},{"nboleto":3, "boleto":"20231012153011", "hashb":"eee56eff0c53ce58d857bfbfd2b459e43b7680c2d0271c64124c3cfc4ff1efcd", "rutab":"boleto-20231012153011-00003.pdf"},{"nboleto":4, "boleto":"20231012153011", "hashb":"ace90389337e01d00d23dce9eace0636dbcf6deda89cc0159cf6eb9a8d4003e4", "rutab":"boleto-20231012153011-00004.pdf"},{"nboleto":5, "boleto":"20231012153011", "hashb":"ac9c94792ecc6ef280db2144d3e8b3ddc82a3818d39a2d77c4dea6342709092e", "rutab":"boleto-20231012153011-00005.pdf"}]   } }
26	{"idbloque":23, "previousHash":"00000ad06207234becf46757ee742855ea391b5d649aea8e85f7f707fa1eacfb","actualhash":"000006e9d55de3b85e9b707c12bde89a000765fe24c8e6bda38aa1ca83b64784","timeStamp":1697466610456,"nonce":471754,"venta":{"idr_bloque":21,"nboleto":3,"boleto":"20231012153011","hashold":"eee56eff0c53ce58d857bfbfd2b459e43b7680c2d0271c64124c3cfc4ff1efcd", "rutaold":"boleto-20231012153011-00003.pdf","cedula":"22222","nombre":"dddd", "telefono":"222","direccion":"ssss","email":"dddd","Fecha Compra":"2023-10-16-09-30-10","usuario":"1" ,  "hashnew":"8c6b5f4142bbfbebe0b42f51a87dbcc82ed5eae5c1ab3dc093d1da5fe8fafb36"    } }
27	{"idbloque":24, "previousHash":"000006e9d55de3b85e9b707c12bde89a000765fe24c8e6bda38aa1ca83b64784","actualhash":"000008680cfa0b556e15f4b9fe2f923a8d0e47ffc22d8a3bb5cad773422eef51","timeStamp":1697475682275,"nonce":574011,"rifa":{"nboleto":3,"boleto":"20231012153011", "premio":"casa","Fecha Compra":"2023-10-16-12-01-22","usuario":"1"   } }
28	{"idbloque":25, "previousHash":"000008680cfa0b556e15f4b9fe2f923a8d0e47ffc22d8a3bb5cad773422eef51","actualhash":"000002522aa6cfc3622093c2e5f5647d1860f10caaa8c51948abc89c38e64a47","timeStamp":1697481672836,"nonce":1460679,"rifa":{"nboleto":3,"boleto":"20231012153011", "premio":"esfero","Fecha Compra":"2023-10-16-13-41-12","usuario":"1"   } }
29	{"idbloque":26, "previousHash":"000002522aa6cfc3622093c2e5f5647d1860f10caaa8c51948abc89c38e64a47","actualhash":"0000021d727e6946544c72c6fc4766a7e7dfacc07c0412cc27b35e3f8d991916","timeStamp":1697482267684,"nonce":119244,"rifa":{"nboleto":2,"boleto":"20231012153011", "premio":"otro","Fecha Compra":"2023-10-16-13-51-07","usuario":"null"   } }
30	{"idbloque":27, "previousHash":"0000021d727e6946544c72c6fc4766a7e7dfacc07c0412cc27b35e3f8d991916","actualhash":"00000e4782e594901edfeeb4a6eac58d626d0994499606cb979da19ccd863e04","timeStamp":1697482292918,"nonce":1285801,"rifa":{"nboleto":1,"boleto":"20231012153011", "premio":"mmmm","Fecha Compra":"2023-10-16-13-51-32","usuario":"1"   } }
\.


--
-- Data for Name: permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permisos (idp, idur, idmr, pms_permiso, pms_centro, pms_observacion, aud_fecha, aud_hora, aud_usuario, aud_centro, aud_estado) FROM stdin;
1	1	0	1	1	\N	\N	\N	1	1	1
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (idu, usr_nombre, usr_nick, usr_clave, tipo, aud_fecha, aud_hora, aud_usuario, aud_centro, aud_estado) FROM stdin;
1	admin	admin	21232f297a57a5a743894a0e4a801fc3	1	\N	\N	1	1	1
2	adr	adr	21232f297a57a5a743894a0e4a801fc3	1	\N	\N	1	1	1
\.


--
-- Name: bloque_idb_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bloque_idb_seq', 30, true);


--
-- Name: permisos_idp_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permisos_idp_seq', 1, true);


--
-- Name: usuario_idu_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_idu_seq', 2, true);


--
-- Name: bloque bloque_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bloque
    ADD CONSTRAINT bloque_pkey PRIMARY KEY (idb);


--
-- Name: permisos permisos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permisos
    ADD CONSTRAINT permisos_pkey PRIMARY KEY (idp);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idu);


--
-- PostgreSQL database dump complete
--

