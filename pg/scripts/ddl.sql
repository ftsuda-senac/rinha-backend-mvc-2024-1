--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1

-- Started on 2024-02-11 14:27:17 UTC

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

--
-- TOC entry 3363 (class 1262 OID 16384)
-- Name: rinha2024q1; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE rinha2024q1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


\connect rinha2024q1

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
-- TOC entry 215 (class 1259 OID 16385)
-- Name: cliente; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    limite integer NOT NULL,
    saldo integer NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 16397)
-- Name: seq_transacao; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_transacao
    START WITH 1
    INCREMENT BY 1000
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 216 (class 1259 OID 16390)
-- Name: transacao; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.transacao (
    id bigint NOT NULL,
    descricao character varying(255),
    tipo character varying(255),
    valor integer,
    realizada_em timestamp(6) without time zone,
    cliente_id integer
);


--
-- TOC entry 3355 (class 0 OID 16385)
-- Dependencies: 215
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.cliente (id, limite, saldo) VALUES (1, 100000, 0);
INSERT INTO public.cliente (id, limite, saldo) VALUES (2, 80000, 0);
INSERT INTO public.cliente (id, limite, saldo) VALUES (3, 1000000, 0);
INSERT INTO public.cliente (id, limite, saldo) VALUES (4, 10000000, 0);
INSERT INTO public.cliente (id, limite, saldo) VALUES (5, 500000, 0);


--
-- TOC entry 3356 (class 0 OID 16390)
-- Dependencies: 216
-- Data for Name: transacao; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_transacao; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_transacao', 1, false);


--
-- TOC entry 3208 (class 2606 OID 16389)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 3210 (class 2606 OID 16396)
-- Name: transacao transacao_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transacao
    ADD CONSTRAINT transacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 16398)
-- Name: transacao fkk44khwkynm4lmqa4ewiaaprdb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transacao
    ADD CONSTRAINT fkk44khwkynm4lmqa4ewiaaprdb FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


-- Completed on 2024-02-11 14:27:18 UTC

--
-- PostgreSQL database dump complete
--


